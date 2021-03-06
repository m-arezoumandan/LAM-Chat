package it.unipi.lam.client2;

import com.ericsson.otp.erlang.OtpErlangDecodeException;
import com.ericsson.otp.erlang.OtpErlangExit;
import it.unipi.lam.entities.User;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class ClientSender implements Runnable{
    private final Client c;
    private final CountDownLatch latch;

    public ClientSender(Client c, CountDownLatch latch) {
        this.c = c;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("To send on the room press r, to private message press p");
            String ans = sc.nextLine();
            if (ans.equals("r")){
                this.c.send(this.c.getChatRoom().getRoomName(), true);
            }
            else if (ans.equals("p")){
                boolean sent = false;
                System.out.println("write the username");
                ans = sc.nextLine();
                List<User> chatUsers = this.c.getChatRoom().getUserList();
                for (User u: chatUsers){
                    if (u.getUsername().equals(ans) && !u.getUsername().equals(this.c.getUser().getUsername())){
                        this.c.send(u.getUsername(), false);
                        sent = true;
                        break;
                    }
                }
                if(!sent) System.out.println("Username does not exist in this chat room");
            }
            latch.countDown();
        } catch (OtpErlangExit | OtpErlangDecodeException otpErlangExit) {
            otpErlangExit.printStackTrace();
        }
    }
}
