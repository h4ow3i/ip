package commands;

public class UserCommand {
    private final String messageToUser;

    public UserCommand(String ...args) {
        StringBuilder sb = new StringBuilder();
        for (String arg: args) {
            sb.append(arg);
            sb.append("\n");
        }
        this.messageToUser = sb.toString();
    }
    /**
     * Returns the string message to user.
     */
    public String getMessageToUser() {
        return this.messageToUser;
    }
}