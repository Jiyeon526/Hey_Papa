package com.ssafy.heypapa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.heypapa.entity.Article;
import com.ssafy.heypapa.entity.ArticleLike;
import com.ssafy.heypapa.entity.Review;
import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.repository.ArticleLikeRepository;
import com.ssafy.heypapa.repository.ArticleRepository;
import com.ssafy.heypapa.repository.ReviewRepository;
import com.ssafy.heypapa.repository.UserRepository;
import com.ssafy.heypapa.request.RegistRequest;
import com.ssafy.heypapa.request.UserModifyRequest;
import com.ssafy.heypapa.request.UserRequest;
import com.ssafy.heypapa.response.MyArticleResponse;
import com.ssafy.heypapa.response.ProfileResponse;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ArticleLikeRepository articleLikeRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	final String[] preNickname = new String[] 
			{"예쁜 ", "멋진 ", "우아한 ", "활발한 ", "고상한 ", "귀여운 ", "다정한 ", "대담한 ", "잘생긴 ", "따뜻한 ", "매력적인 ",
			  "명량한 ", "성실한 ", "신중한 ", "용감한 ", "수줍은 " };

	public User getUserByNickname(String username) {
		Optional<User> user = userRepository.findByNickname(username);
		if(user.isPresent()) {
			System.out.println("service " + user.get().getEmail());
		}
		return user.orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
	
		return user.orElse(null);
	}

	@Override
	public User createUser(RegistRequest req) {
		User user = new User();
		user.setD_day(req.getDDay());
		user.setEmail(req.getEmail());
		user.setRegion(req.getRegion());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setNickname(makeNickname(req.getNickname()));

		user.setWeek(req.getWeek());
		
		// 이미지 저장
		user.setImg("img");
		userRepository.save(user);
		
		return user;
	}

	@Override
	public boolean putUser(long userId, UserModifyRequest req) {
		
		try {
			
			Optional<User> user = userRepository.findById(userId);
			
			if(!user.isPresent()) {
				return false;
			}
			
			user.get().setD_day(req.getDDay());
			user.get().setRegion(req.getRegion());
			user.get().setPassword(passwordEncoder.encode(req.getPassword()));

			if(!user.get().getNickname().equals(req.getNickname())) {
				user.get().setNickname(makeNickname(req.getNickname()));
			}
			
			user.get().setWeek(req.getWeek());
			userRepository.save(user.get());
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public String makeNickname(String nickname) {
		// 닉네임 중복 처리
		
		String nowNickname = nickname;
		String newNickname = nickname;
		Optional<User> dUser = userRepository.findByNickname(nowNickname);
		Random rand = new Random();
		while(dUser.isPresent()) {
			newNickname = preNickname[rand.nextInt(16)] + nowNickname;
			dUser = userRepository.findByNickname(newNickname);
		}
		
		return newNickname;
	}

	@Override
	public ProfileResponse getProfile(long userId) {
		ProfileResponse res = new ProfileResponse();
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent()) {
			return null;
		}
		
		res.setD_day(user.get().getD_day());
		res.setImg(user.get().getImg());
		res.setNickname(user.get().getNickname());
		res.setRegion(user.get().getRegion());
		res.setWeek(user.get().getWeek());
		
		return res;
	}

	@Override
	public List<MyArticleResponse> getArticle(long userId) {
		List<MyArticleResponse> res = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		
		if(user == null) return null;
		
		List<Article> articles = articleRepository.findByUser(user.get());
		for(Article article : articles) {
			MyArticleResponse mArticle = new MyArticleResponse();
			
			mArticle.setContent(article.getContent());
			mArticle.setId(article.getId());
			mArticle.setImg(article.getImg());
			mArticle.setCreated_at(article.getCreated_at());
			
			List<ArticleLike> like = articleLikeRepository.findByArticleId(article.getId());
			mArticle.setLike_cnt(like.size());
			
			List<Review> review = reviewRepository.findByArticleId(article.getId());
			mArticle.setComment_cnt(review.size());
			res.add(mArticle);
		}
		
		return res;
	}

}
