package com.ssafy.heypapa.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.heypapa.entity.Comment;
import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.QuizTypeEnum;
import com.ssafy.heypapa.request.CommentRequest;
import com.ssafy.heypapa.request.MyQuizRequest;
import com.ssafy.heypapa.request.QuizRequest;
import com.ssafy.heypapa.response.QuizResponse;
import com.ssafy.heypapa.service.QuizService;
import com.ssafy.heypapa.util.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "퀴즈 api", tags = { "Quiz" })
@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
//	@GetMapping("/{type}")
//	@ApiOperation(value = "퀴즈 전체 목록", notes = "<strong>퀴즈 전체 리스트</strong>")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//        @ApiResponse(code = 401, message = "토큰 인증 실패"),
//        @ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<List<QuizRequest>> getAllQuiz(Pageable pageable) {
//		List<QuizRequest> quizList = quizService.getAllQuiz(pageable);
//		return ResponseEntity.status(200).body(quizList);
//	}
	
	@GetMapping("/{quizId}/{user_id}")
	@ApiOperation(value = "퀴즈 상세", notes = "<strong>하나의 퀴즈 보기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<QuizResponse> getOneQuiz(@PathVariable(name = "quizId") Long id, @PathVariable("user_id") long userId) {
		QuizResponse quiz = quizService.getoneQuiz(id, userId);
		return ResponseEntity.status(200).body(quiz);
	}
	
//	@GetMapping("{quizId}/getImg")
//	public String getImg(@PathVariable(name = "quizId") Long id) {
//		Optional<Quiz> result = quizService.getQuizImg(id);
//		if(result.isPresent()==false) {
//			return "등록이미지 없음";
//		} else {
//			return result.get().getImg();
//		}
//	}
	
	// 퀴즈 댓글
	@PostMapping("/{quizId}")
	@ApiOperation(value = "퀴즈 댓글 작성", notes = "<strong>퀴즈 하나당 댓글 달기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> createComment(@PathVariable(name = "quizId") Long id, 
			@RequestBody CommentRequest comment) {
		Comment comments = quizService.createComment(id, comment);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PutMapping("/{quizId}/{commentId}")
	@ApiOperation(value = "댓글 수정", notes = "<strong>퀴즈 댓글 내용 수정하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<CommentRequest> updateComment(@PathVariable(name = "quizId") Long qId,
			@PathVariable(name = "commentId") Long cId, @RequestBody CommentRequest commentRequest) {
		Comment com = quizService.updateComment(qId, cId, commentRequest);
		return ResponseEntity.status(200).body(commentRequest);
	}
	
	@DeleteMapping("/{quizId}/{commentId}")
	@ApiOperation(value = "퀴즈 댓글 삭제", notes = "<strong>퀴즈 댓글 삭제 하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> deleteComment(@PathVariable(name = "quizId") Long qId,
			@PathVariable(name = "commentId") Long cId) {
		quizService.deleteComment(qId, cId);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/wife")
	@ApiOperation(value = "아내 종류별 퀴즈", notes = "<strong>아내 종류별 퀴즈 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllWifeQuiz() {
		List<QuizRequest> quizList = quizService.getAllWifeQuiz();
		return ResponseEntity.status(200).body(quizList);
	}
	
	@GetMapping("/baby")
	@ApiOperation(value = "아기 종류별 퀴즈", notes = "<strong>아기 종류별 퀴즈 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllBabyQuiz() {
		List<QuizRequest> quizList = quizService.getAllBabyQuiz();
		return ResponseEntity.status(200).body(quizList);
	}
	
	@GetMapping("/food")
	@ApiOperation(value = "음식 종류별 퀴즈", notes = "<strong>음식 종류별 퀴즈 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllFoodQuiz() {
		List<QuizRequest> quizList = quizService.getAllFoodQuiz();
		return ResponseEntity.status(200).body(quizList);
	}
	
	@GetMapping("/society")
	@ApiOperation(value = "사회 종류별 퀴즈", notes = "<strong>사회 종류별 퀴즈 리스트</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<QuizRequest>> getAllSocietyQuiz() {
		List<QuizRequest> quizList = quizService.getAllSocietyQuiz();
		return ResponseEntity.status(200).body(quizList);
	}
	
	@PostMapping("/{quizId}/myquiz")
	@ApiOperation(value = "나의 퀴즈", notes = "<strong>나의 퀴즈로 찜하기</strong>")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "토큰 인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<BaseResponseBody> likeQuiz(@RequestBody MyQuizRequest myquizRequest, @PathVariable(name = "quizId") Long id) {
		quizService.myQuiz(myquizRequest, id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
	}
	
}
