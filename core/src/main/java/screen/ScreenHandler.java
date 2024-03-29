package screen;

import enums.ScreenType;

public class ScreenHandler 
{
	private ScreenType state = ScreenType.MENU; // Define which screen is ON

	public ScreenType getState()
	{
		return state;
	}
	
	public void setState(ScreenType state)
	{
		this.state = state;
	}
}
