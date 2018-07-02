package com.revature.andrewduffey.bank.bean;

import com.revature.andrewduffey.bank.App;
import com.revature.andrewduffey.bank.service.AccountService;
import com.revature.andrewduffey.bank.service.UserInfoService;
import com.revature.andrewduffey.bank.service.UserService;
import org.apache.log4j.Logger;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AdminUser extends User {
    final static Logger logger = Logger.getLogger(AdminUser.class);
    private static final long serialVersionUID = 2105587968682648018L;

    public AdminUser(int id, String username){
        super(id, username);
        admin = true;
    }

    @Override
    protected boolean processCommand(String command) {
        switch (command.toLowerCase()) {
            case "logout":
                return false;
            case "approve":
                List<Integer> users = UserInfoService.getPendingUserIds();

                for (Integer id : users) {
                    String username = UserService.getUserName(id);
                    System.out.print("Approve " + username + " [y/n]: ");
                    String result = App.scanner.nextLine().toLowerCase();
                    if (result.startsWith("y")) {
                        UserInfoService.setPending(id,false);
                        AccountService.createAccount(id);
                        logger.info("Admin accepted: " + username);
                    } else {
                        UserInfoService.setLocked(id, true);
                        logger.info("Admin declined: " + username);
                    }
                }
                break;
            case "help":
                printHelp();
                break;
            case "quit":
                App.quit = true;
                return false;
            default:
                System.out.println("Error: Invalid Command!\nType 'help' for a list of valid commands.");
                break;
        }
        return true;
    }

    @Override
    protected void printHelp() {
        logger.info("Help screen displayed");
        System.out.println("Logout: logout");
        System.out.println("Approve: approve");
        System.out.println("Help: help");
        System.out.println("Quit: quit");
    }
}
