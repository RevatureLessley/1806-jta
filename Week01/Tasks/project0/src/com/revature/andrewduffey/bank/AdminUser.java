package com.revature.andrewduffey.bank;

import org.apache.log4j.Logger;

import java.io.File;

public class AdminUser extends User {
    final static Logger logger = Logger.getLogger(AdminUser.class);
    private static final long serialVersionUID = 2105587968682648018L;

    public AdminUser(String username, String password) {
        super(username, password);
        admin = true;
    }

    @Override
    protected boolean processCommand(String command) {
        switch (command.toLowerCase()) {
            case "logout":
                return false;
            case "approve":
                File dir = new File(App.PENDING_PATH);
                if (dir.listFiles().length == 0) {
                    System.out.println("No users to approve");
                }
                for(File file : dir.listFiles()) {
                    String filename = file.getName();
                    if (file.isFile() && filename.endsWith(".ser")) {
                        String username = filename.substring(0, filename.length() - ".ser".length());
                        System.out.print("Approve " + username + " [y/n]: ");
                        String result = App.scanner.nextLine().toLowerCase();
                        if (result.startsWith("y")) {
                            User.serialize(User.deserialize(username, App.PENDING_PATH),App.USERS_PATH);
                            logger.info("Admin accepted: " + username);
                        } else {
                            logger.info("Admin declined: " + username);
                        }
                        file.delete();
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
