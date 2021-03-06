package com.ssafy.heypapa.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReviewRequest")
public class ReviewRequest {
	@ApiModelProperty(name = "유저id")
	private Long user_id;
	@ApiModelProperty(name = "리뷰 내용")
	private String content;
}
