package plugin.api.application.sheet.events;


public interface ValueListener {
	
	public void valueChanged(ValueEventAPI event);
	
	public void tempValueChanged(ValueEventAPI event);

}
