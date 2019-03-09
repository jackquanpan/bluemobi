package cn.bluemobi.dao.impl;

import org.springframework.stereotype.Component;

import cn.bluemobi.dao.GenreDao;

@Component
public class GenreDaoImpl implements GenreDao {
	// @Autowired
	// BaseDao dao;
	//
	// public List<Genre> getGenreList(Genre genre, Page page) {
	// if(page.getPageCount()==-1){
	// dao.findPageSum("SELECT COUNT(*) from genre", page);
	// if(page.getTotalCount()==0){
	// return null;
	// }
	// }
	// List<Genre> genreList =
	// dao.findByPage("select A.id,A.remark,A.name as name,B.name as pname from genre A,genre B where A.parent = B.id ",
	// page, Genre.class);
	// return genreList;
	// }
	//
	// public List<Genre> getPGenre() {
	// return dao.findForList("select * from genre ",Genre.class);
	// }
	//
	// public List<Genre> getAllGenre() {
	// return dao.findForList("select * from genre ",Genre.class);
	// }
	//
	// public int getGenreParentId(String id){
	// return dao.findForInt("select grade from genre where id = "+id);
	// }
	//
	// public Long addGenre(Genre genre) {
	// return
	// dao.saveGetKey("INSERT into `genre` SET `name`=:name,`parent`=:parent,`grade`=:grade,`remark`=:remark,`cover`=:cover ,creattime=now()",
	// genre);
	// }
	// /**
	// * 删除权限
	// */
	// public void delGenre(Long id) {
	// dao.executeByParams("DELETE FROM `genre` where `id` = ?", id);
	// }
	//
	// public List<Genre> getGenreParentId(long l) {
	// return
	// dao.findForList("select * from genre where parent=?",Genre.class,l);
	// }
	// /**
	// * 查找权限总数
	// */
	// public int getCount() {
	// return dao.findForInt("select count(*) from genre");
	// }
	//
	// public void updateGenre(Genre genre) {
	// dao.executeByObject(" update genre set `name`=:name,`remark`=:remark,`cover`=:cover where id=:id",
	// genre);
	//
	// }
	//
	// public List<Conditions> getAppAllGenre() {
	// List<Conditions> conditions=
	// dao.findForList("select g.name,g.id,g.parent as pid ,(select count(b.id) from business b where b.type=g.id) as sumCount from genre g where g.parent>0",Conditions.class);
	// List<Conditions>
	// tions=dao.findForList("select g.name,g.id,g.parent as pid ,(select count(b.id) from business b where b.type in (select e.id from genre e where e.parent=g.id )) as sumCount from genre g where g.parent=0",
	// Conditions.class);
	// conditions.addAll(tions);
	// return conditions;
	// }
	//
	//
	// public List<Conditions> getAllArea(String param) {
	// List<Conditions> conditions=
	// dao.findForList("select a.name,a.id,a.parent as pid , (select count(b.id) from business b where b.area=a.id) as sumCount from area a where a.parent=(select e.id from area e where e.name=?) ",Conditions.class,param);
	// List<Conditions>
	// tions=dao.findForList("select a.name,a.id,0 as pid , (select count(b.id) from business b where b.area in (select r.id from area r where r.parent=a.id)) as sumCount  from area a where a.name=?",
	// Conditions.class, param);
	// conditions.addAll(tions);
	// return conditions;
	// }
	// /**
	// * 微信查询分类列表
	// */
	// public List<Genre> findWeiXinGenreList() {
	// List<Genre>
	// list=dao.findForList("select cover,id,name,parent,grade from genre",
	// Genre.class);
	// return list;
	// }
	//
	// public Conditions getAllCount() {
	// return dao.findForObject("select count(*) as sumCount from business b",
	// Conditions.class);
	// }
	//
	// public Genre getColumn(Long id) {
	//
	// return
	// dao.findForObject("SELECT  g.type,r.id as pid,r.`name` as pname,e.id,e.`name` from group_sale g LEFT JOIN genre e ON g.genre=e.id LEFT JOIN genre r ON e.parent=r.id where g.id=?",
	// Genre.class,id);
	// }
	// /**
	// * 查询所有分类
	// */
	// public List<Genre> findGenreList() {
	// return dao.findForList("  select id,name from genre where grade=2 ",
	// Genre.class);
	// }
	//
	//
	//
	//

}
