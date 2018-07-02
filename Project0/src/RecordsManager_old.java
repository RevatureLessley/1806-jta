import java.io.*;

public class RecordsManager_old {

    private FileInputStream fileInputter;
    private FileOutputStream fileOutputter;
    private ObjectInputStream objectInputter;
    private ObjectOutputStream objectOutputter;


    public AccountsRecord getFile()
    {
        AccountsRecord record;
        try
        {
            fileInputter  = new FileInputStream("accounts.ser");
            objectInputter = new ObjectInputStream(fileInputter);
            record = (AccountsRecord) objectInputter.readObject();
            objectInputter.close();
            fileInputter.close();

            return record;
        }
        catch(IOException e)
        {
            //TODO: Log this instead
//            System.err.println("Account file not found!!");
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Class not found!");
        }

        return new AccountsRecord();
    }

    public void saveFile(AccountsRecord record)
    {
        try
        {
            fileOutputter = new FileOutputStream("accounts.ser");
            objectOutputter = new ObjectOutputStream(fileOutputter);
            objectOutputter.writeObject(record);
            objectOutputter.close();
            fileOutputter.close();
        }
        catch(IOException e)
        {
            System.err.println("Accounts file could not be saved");
            e.printStackTrace();
        }
    }
}
