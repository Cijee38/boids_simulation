public class MessageEvent extends Event {
    private String mess;
    public MessageEvent(int date, String message){
	super(date);
	this.mess = message;
    }

    public void execute() {
	System.out.println(this.getDate() + this.mess);
    }
}
