package views;

import components.*;

public interface MapElement {
	String getName();

	Coordinates getCoordinates();

	String getTooltipInfo();

	void open();
}
