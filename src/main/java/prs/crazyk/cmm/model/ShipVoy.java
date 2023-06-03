package prs.crazyk.cmm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipVoy {
	private String shipVoy;
	private String vvd;
	private String vvdYear;

	public ShipVoy(String shipVoy) {
		int lastDashPosition = shipVoy.lastIndexOf("-");
		this.vvd = shipVoy.substring(0, lastDashPosition);
		this.vvdYear = shipVoy.substring(lastDashPosition + 1);
		this.shipVoy = shipVoy;
	}
}
