package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.community.Board;

@Mapper
@Repository
public interface CommunityMapper {
	
	public int addPost(Board board);
	
	public List<Board> getPostList();
	
	public Board getPost(Board board);
	
	public void updatePostViewCount(Board board);
	
	public void updatePostLike(Map map);
	
	public void updateCmntLike(Map map);


}