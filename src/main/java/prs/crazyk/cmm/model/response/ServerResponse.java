package prs.crazyk.cmm.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse<T> {
	private boolean hasSucceed;
	private T body;
}
