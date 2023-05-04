package com.app.prayer.serviceImp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.prayer.dto.FilterBean;
import com.app.prayer.dto.PrayerRequestDto;
import com.app.prayer.entity.Category;
import com.app.prayer.entity.Prayer_Request;
import com.app.prayer.entity.User;
import com.app.prayer.exception.ResourceNotFoundException;
import com.app.prayer.repository.CategoryListRepository;
import com.app.prayer.repository.PrayerRequestRepository;
import com.app.prayer.repository.UserRepository;
import com.app.prayer.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService{
	
	
	@Autowired
	private PrayerRequestRepository prayerRequestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryListRepository categoryRepository;

	
	
	@Autowired
	private ModelMapper modelMapper;
	
	 @PersistenceContext
	    private EntityManager entityManager;
	
	@Override
	public PrayerRequestDto createRequest(PrayerRequestDto requestDto, Integer userid, Integer categoryid) {
		Date date = new Date();
 		SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm:ss");
 		String currentdate = formatter.format(date);
 	  
		 User user = this.userRepository.findById(userid)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userid));

	        Category category = this.categoryRepository.findById(categoryid)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryid));

	        Prayer_Request prayerrequest = this.modelMapper.map(requestDto, Prayer_Request.class);
	        prayerrequest.setUser(user);
	        prayerrequest.setCity(user.getCity());
	        prayerrequest.setCountry(user.getCountry());
	        prayerrequest.setState(user.getState());
			//prayerrequest.setPrayer_request(prayer_request);
	        prayerrequest.setCategory(category);
	        prayerrequest.setCreation_date(currentdate);
	        
	        Prayer_Request newRequest = this.prayerRequestRepository.save(prayerrequest);

	        return this.modelMapper.map(newRequest, PrayerRequestDto.class);
	}

	@Override
	public List<PrayerRequestDto> getAllRequest() {
		List<Prayer_Request> alllist = this.prayerRequestRepository.findAll();
		List<PrayerRequestDto> retDtos = alllist.stream().map((request) -> this.modelMapper.map(request, PrayerRequestDto.class))
        .collect(Collectors.toList());

return retDtos;
	}

	@Override
	public PrayerRequestDto getRequestByid(Integer id) {
		Prayer_Request request = this.prayerRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prayer_Request", "PrayerRequest id", id));
        return this.modelMapper.map(request, PrayerRequestDto.class);
	}

	@Override
	public List<PrayerRequestDto> getRequestByCategory(Integer categoryid) 
	{
		Category category =this.categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "category id", categoryid));
		List<Prayer_Request> request =this.prayerRequestRepository.findByCategory(category);
		List<PrayerRequestDto> requestDtos = request.stream().map((r) -> this.modelMapper.map(r, PrayerRequestDto.class)).collect(Collectors.toList());
        return requestDtos;
	}

	@Override
	public List<PrayerRequestDto> getRequestByUser(Integer userid) {
		User user = this.userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userid));
        List<Prayer_Request> request = this.prayerRequestRepository.findByUser(user);
        List<PrayerRequestDto> requestDtos = request.stream().map((r) -> this.modelMapper.map(r, PrayerRequestDto.class)).collect(Collectors.toList());
        return requestDtos;
	}

	/*
	@Override
	public List<PrayerRequestDto> searchRequest(String keyword) {
		  //List<Prayer_Request> req = this.prayerRequestRepository.findByRequest(keyword);
		  List<Prayer_Request> req = this.prayerRequestRepository.searchByRequest("%" + keyword + "%");
	      List<PrayerRequestDto> requestDtos = req.stream().map((search) -> this.modelMapper.map(search, PrayerRequestDto.class)).collect(Collectors.toList());
	        return requestDtos;
	}	
	*/

	@SuppressWarnings("unchecked")
	@Override
	public List<PrayerRequestDto> searchRequest(FilterBean filterBean) 
	{
		String query = "Select c.id as case_id, c.serial_number as serial_number, c.transaction_date as transaction_date, c.date as date "
    			+ ", head.id as h_id, head.filing_date as filing_date, head.status_code as status_code, head.status_date as status_date, head.mark_identification as mark_identification, head.mark_drawing_code as mark_drawing_code, head.published_for_opposition_date as published_for_opposition_date, head.attorney_name as attorney_name "
    			+ ", head.current_location as current_location, head.location_date as location_date, head.employee_name as employee_name "
    			+ ", cor.address_1 as address_1, cor.address_2 as address_2, cor.address_3 as address_3, cor.address_4 as address_4, cor.address_5 as address_5"
    			+ " from case_file_header head join  head.caseData c "
    			+ " inner join correspondent cor on cor.case_data_id = c.id "
    			//+ " where c.serial_number = '90819890' "
    			+ " ";
		
		query = "select c from Prayer_Request c ";    	
    	String whereSql = "";
    	if(filterBean!=null)
    	{
    		if(filterBean.getUserid()!=null && filterBean.getUserid().length()!=0) {
    			whereSql = whereSql + "and c.userid = "+filterBean.getUserid()+" ";
    		}    		
    		if(filterBean.getCategoryid()!=null && filterBean.getCategoryid().length()!=0) {
    			whereSql = whereSql + "and c.category = "+filterBean.getCategoryid()+" ";
    		}
    		if(filterBean.getKeyword()!=null && filterBean.getKeyword().length()!=0) {
    			whereSql = whereSql + "and c.prayer_request_note like '%"+filterBean.getKeyword()+"%' ";
    		}
    		if(filterBean.getCity()!=null && filterBean.getCity().length()!=0) {
    			whereSql = whereSql + "and c.city = '"+filterBean.getCity()+"' ";
    		}
    		if(filterBean.getCountry()!=null && filterBean.getCountry().length()!=0) {
    			whereSql = whereSql + "and c.country = '"+filterBean.getCountry()+"' ";
    		}
    		if(filterBean.getState()!=null && filterBean.getState().length()!=0) {
    			whereSql = whereSql + "and c.state = '"+filterBean.getState()+"' ";
    		}
    		
    	}
    	
    	if (whereSql.length()!=0)
    	{
    		if(whereSql.trim().startsWith("and"))
    		{
    			whereSql = whereSql.replaceFirst("and", "");
    		}
    		
    		whereSql = " where "+whereSql;
    	}
    	System.out.println("RequestServiceImpl.searchRequest()"+ whereSql);
		List<Prayer_Request> req = entityManager.createQuery(query + whereSql).getResultList();
		
		
		
		//  List<Prayer_Request> req = this.prayerRequestRepository.searchByRequest("%"+filterBean.getKeyword()+"%");
	      List<PrayerRequestDto> requestDtos = req.stream().map((search) -> this.modelMapper.map(search, PrayerRequestDto.class))
	    		  .collect(Collectors.toList());
	      return requestDtos;
	}

}
