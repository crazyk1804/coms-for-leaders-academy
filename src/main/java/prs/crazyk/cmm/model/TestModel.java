package prs.crazyk.cmm.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestModel {
	@NotNull(message = "NOT NULL")
	@ApiModelProperty(name = "테스트 값", required = true, value = "abc", example = "123")
	private String testValue;
	@Min(-1)
	@ApiModelProperty(name="SECONDVALUE")
	private int number;
}
