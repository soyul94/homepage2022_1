package egovframework.let.crud2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.crud2.service.Crud2Service;
import egovframework.let.crud2.service.Crud2VO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service(value="crud2Service")
public class Crud2ServiceImpl implements Crud2Service {
	
	@Resource(name="crud2Mapper")
	Crud2Mapper crud2Mapper;
	
	@Resource(name="egovBtCrudIdGnrService")
	private EgovIdGnrService idgenService;

	@Override
	public Crud2VO selectCrud2(Crud2VO crud2vo) throws Exception {
		return crud2Mapper.selectCrud2(crud2vo);
	}

	@Override
	public int selectCrud2ListCnt(Crud2VO crud2vo) throws Exception {
		return crud2Mapper.selectCrud2ListCnt(crud2vo);	
	}

	@Override
	public List<EgovMap> selectCrud2List(Crud2VO crud2vo) throws Exception {
		return crud2Mapper.selectCrud2List(crud2vo);
	}

	@Override
	public String insertCurd2(Crud2VO crud2vo) throws Exception {
		String id = idgenService.getNextStringId(); //얘는 왜 하필 impl에 들어 있을까 ?
		crud2vo.setCrudId(id);							//에러가 발생했을 시에 모두 롤백이 가능하기 때문에 (impl은 오토커밋이 아니다.)
		crud2Mapper.insertCrud2(crud2vo);
		return id;
	}

	@Override
	public void updateCrud2(Crud2VO crud2vo) throws Exception {
		crud2Mapper.updateCrud2(crud2vo);
	}
	
	@Override
	public void deleteCurd2(Crud2VO crud2vo) throws Exception {
		crud2Mapper.deleteCrud2(crud2vo);
	}


}
