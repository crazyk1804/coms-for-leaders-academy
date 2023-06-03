package prs.crazyk.cmm.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class XYPoint {
	private double x;
	private double y;

	public double[] toArray() {
		return new double[]{x, y};
	}
}
