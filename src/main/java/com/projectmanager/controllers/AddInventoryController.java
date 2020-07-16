package com.projectmanager.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.dao.MappingsDao;
import com.projectmanager.dao.ValvesDao;
import com.projectmanager.entity.Mappings;
import com.projectmanager.entity.Valves;
import com.projectmanager.model.InventoryMappingModel;

@Controller
@EnableWebMvc
public class AddInventoryController {
	
	@Autowired
	MappingsDao mappingsDao;
	
	@Autowired
	ValvesDao valvesDao;

	@RequestMapping(value = "/inventoryDetails", method = RequestMethod.GET)
	private ModelAndView inventoryDetails(){
		ModelAndView modelAndView = new ModelAndView();
		//take mapping details  from db for loop 
		ArrayList<String> inventoryList = mappingsDao.getAllInventory();
		System.out.println(inventoryList);
		StringBuffer mappingDetails = new StringBuffer();
		int index = 1;
		for(String inventory1 : inventoryList){
			String inventory = inventory1;
			System.out.println(inventory);
			
			ArrayList<String> materialList = mappingsDao.getMaterial(inventory1);
			System.out.println(materialList);
			
			for(String material : materialList){
					System.out.println(material);
					
					ArrayList<String> typeList = mappingsDao.getType(inventory,material);
					String type = typeList.toString().replace("[","").replace("]", "");
					System.out.println(type);
					
					ArrayList<String> classOrGradeList = mappingsDao.getClassOrGrade(inventory,material);
					String classOrGrade = classOrGradeList.toString().replace("[","").replace("]", "");
					System.out.println(classOrGrade);
					
					ArrayList<String> categoryList = mappingsDao.getCategory(inventory,material);
					String category = categoryList.toString().replace("[","").replace("]", "");
					System.out.println(category);
					
					
					//int index = 1;
				
						mappingDetails.append("<tr>");
						mappingDetails.append("<td><input type='text' style='width:50px' class='form-control' value='" + index + "' name='ItemId'  ></td>");
						mappingDetails.append("<td><input type='checkbox' class='chkView' ></td>");
						mappingDetails.append("<td><input type='text'  class='form-control' value='" + inventory + "' name='inventoryName'  ></td>");
						mappingDetails.append("<td><input type='text'  class='form-control' value='" + material + "' name='material' ></td>");
						mappingDetails.append("<td><input type='text'  class='form-control' value='" + type + "' name='type' disabled ></td>");
						mappingDetails.append("<td><input type='text'  class='form-control' value='" + classOrGrade + "' name='classOrGrade' disabled ></td>");
						mappingDetails.append("<td><input type='text'  class='form-control' value='" + category + "' name='catogory' disabled ></td>");
						mappingDetails.append("</tr>");
						index++;
				}
					
		}
	
		ArrayList<Valves> valvesData = valvesDao.getValveDetails();
		
		StringBuffer valvesDetails = new StringBuffer();
		int index2 = 1;
		for(Valves valves : valvesData){
			valvesDetails.append("<tr>");
			valvesDetails.append("<td>" + index2 + "</td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getModel() + "' name='model' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getMaterial() + "' name='material' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getEnd() + "' name='end' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getType() + "' name='type' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getPressureRatings() + "' name='pressureRatings' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getMaxInletPressure() + "' name='maxInletPressure' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getOperation() + "' name='operation' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getSeatAndSeals() + "' name='seatAndSeals' ></td>");
			valvesDetails.append("<td><input type='text'  class='form-control' value='" + valves.getSizeRange() + "' name='sizeRange' ></td>");
			valvesDetails.append("</tr>");
			index2++;
		}

		modelAndView.setViewName("inventoryDetails");
		modelAndView.addObject("mappingDetails", mappingDetails.toString());
		modelAndView.addObject("valvesDetails", valvesDetails.toString());
		return modelAndView;
	}
	
	public static String[] concatTwoStringArrays(String[] s1, String[] s2){
	    String[] result = new String[s1.length+s2.length];
	    int i;
	    for (i=0; i<s1.length; i++)
	        result[i] = s1[i];
	    int tempIndex =s1.length; 
	    for (i=0; i<s2.length; i++)
	        result[tempIndex+i] = s2[i];
	    return result;
	}//concatTwoStringArrays().
	

	public ArrayList<String> GetUniqueData(boolean IsModified,List<String> ArraryfromDb, List<String> ArrayfromInput)
	{
		ArrayList<String> ResultArray = new ArrayList<String>();
		
		
		
		if(IsModified)
		{
			int LengthOfArraryfromDb = ArraryfromDb.size();
			int LenghtOfArrayfromInput = ArrayfromInput.size();
			
			if(LenghtOfArrayfromInput >LengthOfArraryfromDb || LenghtOfArrayfromInput==LengthOfArraryfromDb)
			{
				
				for(int i=0; i<LenghtOfArrayfromInput;i++)
				{
					String val = ArrayfromInput.get(i);
					for(int j=0; j<LengthOfArraryfromDb;j++)
					{
						if(ArraryfromDb.contains(ArrayfromInput.get(i))|| val.equals("[")||val.equals("]"))
						{
							continue;
						}
						else
						{
							if(!ResultArray.contains(val.trim()))
							{
							
								//String SeprateValue = val.trim()+",";
								ResultArray.add(val.trim());
							}
							
						}
					}
					
				}
			}
			
			else if(LenghtOfArrayfromInput < LengthOfArraryfromDb)
			{
				for(int i=0; i< LengthOfArraryfromDb;i++)
				{
					
					for(int j=0; j<LenghtOfArrayfromInput;j++)
					{
						//String Val1 = ArraryfromDb.get(j);
						String val = ArrayfromInput.get(j);
						if(ArraryfromDb.contains(val)|| val.equals("[")||val.equals("]"))
						{
							continue;
						}
						else
						{
							if(!ResultArray.contains(val.trim()))
							{
							
								//String SeprateValue = val.trim()+",";
								ResultArray.add(val.trim());
							}
							
						}
					}
					
				}
			}
			
		}
		return ResultArray;
	}
	
	
	private ArrayList<String> CheckValid(String PsUserInput, String PsDatabaseValue)
	{
		 
		  List<String> DataFromInput = Arrays.asList(PsUserInput.trim().split(","));
		  List<String> DataFromDb = Arrays.asList(PsDatabaseValue.trim().split(","));
	      boolean LbIsModified= !DataFromInput.equals(DataFromDb);
	      ArrayList<String> NewlyAddedValue = GetUniqueData(LbIsModified,DataFromDb,DataFromInput);	      	     
	      
	      return NewlyAddedValue;
	      
	}
	
	
	//public InventoryMappingModel ModifiedMapping() 
	
	@PostMapping("/updateMappingDetails")
	public @ResponseBody String updateMappingDetails(@RequestBody String dataArrayToSend) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException{
		
		
		String decodedJson = java.net.URLDecoder.decode(dataArrayToSend, "UTF-8");
	      ObjectMapper jacksonObjectMapper = new ObjectMapper(); // This is Jackson
	      List<InventoryMappingModel> userRolesGUIBeans =  jacksonObjectMapper.readValue(decodedJson, new TypeReference<List<InventoryMappingModel>>(){});
		
	      
	      for(InventoryMappingModel InventoryModel:userRolesGUIBeans)
	      {
	    	  ArrayList<String> ArrayOfType = new ArrayList<String>() ;
	    	  ArrayList<String> ArrayOfClassGrade = new ArrayList<String>();
	    	  ArrayList<String> ArrayOfCatogory = new ArrayList<String>();
	    	  
	    	  String InventoryName = InventoryModel.getInventoryName();
	    	  String Material = InventoryModel.getMaterial();
	    	  String Type = InventoryModel.getType();
	    	  String ClassOrGrade = InventoryModel.getClassOrGrade();
	    	  String Catogory = InventoryModel.getCatogory();
	    	  
	  
	    	  ArrayList<Mappings> ListOfAllMappingDetails = mappingsDao.getMappingDetails(InventoryName,Material);
	    	  
	    	  
	    	  for(Mappings MappData:ListOfAllMappingDetails)
	    	  
	    	  {
	    		  ArrayOfType.add(MappData.getType());
	    		  ArrayOfClassGrade.add(MappData.getClassOrGrade());
	    		  ArrayOfCatogory.add(MappData.getCatogory());
	    	  }
	    	  
	    	  
	    	  ArrayList<String> LbTypeNewValue = CheckValid(Type,ArrayOfType.toString().replace("[","").replace("]", ""));
	    	  ArrayList<String> LbClassOrGrade = CheckValid(ClassOrGrade,ArrayOfClassGrade.toString().replace("[","").replace("]", ""));
	    	  ArrayList<String> LbCatogory = CheckValid(Catogory,ArrayOfCatogory.toString().replace("[","").replace("]", ""));
	    	  
	    	  if(LbTypeNewValue.size()==0 && LbClassOrGrade.size()==0 && LbCatogory.size()==0)
		    	 {
		    		    Mappings mapp = null;			    	    
		    					    		   
		    			 int val = mappingsDao.getMaxId();  	
		    			    	mapp = new Mappings(val+1,InventoryName, Material, "Null","Null", "Null");
		    			    	mappingsDao.updateMapping(mapp);
		    			    				    	  
		    	 }
	    	  else if(LbTypeNewValue.size()>0 && LbClassOrGrade.size()==0 && LbCatogory.size()==0)
	    	 {
	    		    Mappings mapp = null;			    	    
	    			for(String NewValue : LbTypeNewValue){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mapp = new Mappings(val+1,InventoryName, Material, NewValue,"Null", "Null");
	    			    	mappingsDao.updateMapping(mapp);
	    			}
	    				    	  
	    	 }
	    	 
	    	 else if(LbTypeNewValue.size()==0 && LbClassOrGrade.size()>0 && LbCatogory.size()==0)
	    	 {
	    		    Mappings mapp = null;			    	    
	    			for(String NewValue : LbClassOrGrade){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mapp = new Mappings(val+1,InventoryName, Material, "Null",NewValue, "Null");
	    			    	mappingsDao.updateMapping(mapp);
	    			}
	    				    	  
	    	 }
	    	  
	    	 else if(LbTypeNewValue.size()==0 && LbClassOrGrade.size()==0 && LbCatogory.size()>0)
	    	 {
	    		    Mappings mapp = null;			    	    
	    			for(String NewValue : LbCatogory){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mapp = new Mappings(val+1,InventoryName, Material, "Null","Null", NewValue);
	    			    	mappingsDao.updateMapping(mapp);
	    			}
	    				    	  
	    	 }
	    	  
	    	 
	    	 else if(LbTypeNewValue.size()>0 && LbClassOrGrade.size()>0 && LbCatogory.size()==0)
	    	 {
	    		  	    		    
	    			if(LbTypeNewValue.size()>LbClassOrGrade.size() ||LbTypeNewValue.size()==LbClassOrGrade.size())
	    		    {
	    		    	for(String NewType : LbTypeNewValue){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewClass : LbClassOrGrade)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType(NewType);
	    		    		objTempMapping.setClassOrGrade(NewClass);
	    		    		objTempMapping.setCatogory("Null");
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }
	    		    else if(LbClassOrGrade.size()>LbTypeNewValue.size())
	    		    {
	    		    	for(String NewClass  : LbClassOrGrade ){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewType  : LbTypeNewValue)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType(NewType);
	    		    		objTempMapping.setClassOrGrade(NewClass);
	    		    		objTempMapping.setCatogory("Null");
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }	    	  
	    	 }
	    	 
	    	 else if(LbTypeNewValue.size()==0 && LbClassOrGrade.size()>0 && LbCatogory.size()>0)
	    	 {
	    		 
	    		    if(LbClassOrGrade.size()>LbCatogory.size() ||LbClassOrGrade.size()==LbCatogory.size())
	    		    {
	    		    	for(String NewClass : LbClassOrGrade){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewCatogory : LbCatogory)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType("Null");
	    		    		objTempMapping.setClassOrGrade(NewClass);
	    		    		objTempMapping.setCatogory(NewCatogory);
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }
	    		    else if(LbCatogory.size()>LbClassOrGrade.size())
	    		    {
	    		    	for(String NewCatogory  : LbCatogory ){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewClass  : LbClassOrGrade)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType("Null");
	    		    		objTempMapping.setClassOrGrade(NewClass);
	    		    		objTempMapping.setCatogory(NewCatogory);
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }	    	  
	    	 }
	    	 
	    	 else if(LbTypeNewValue.size()>0 && LbClassOrGrade.size()==0 && LbCatogory.size()>0)
	    	 {
	    		  
	    		    if(LbTypeNewValue.size()>LbCatogory.size() ||LbTypeNewValue.size()==LbCatogory.size())
	    		    {
	    		    	for(String NewType : LbTypeNewValue){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewCatogory : LbCatogory)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType(NewType);
	    		    		objTempMapping.setClassOrGrade("Null");
	    		    		objTempMapping.setCatogory(NewCatogory);
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }
	    		    else if(LbCatogory.size()>LbTypeNewValue.size())
	    		    {
	    		    	for(String NewCatogory  : LbCatogory ){
	    		    		Mappings objTempMapping = new Mappings();
	    		    		for(String NewType  : LbTypeNewValue)
	    		    		{	    		    			    		    		
	    		    		objTempMapping.setId(mappingsDao.getMaxId()+1);
	    		    		objTempMapping.setInventoryName(InventoryName);
	    		    		objTempMapping.setMaterial(Material);
	    		    		objTempMapping.setType(NewType);
	    		    		objTempMapping.setClassOrGrade("Null");
	    		    		objTempMapping.setCatogory(NewCatogory);
	    		    		mappingsDao.updateMapping(objTempMapping);
	    		    	    objTempMapping = new Mappings();
	    		    		}
	    		    		
	    		    	}
	    		    }	    	  
	    	 }
	    	 
	    	else if(LbTypeNewValue.size()>0 && LbClassOrGrade.size()>0 && LbCatogory.size()>0)
	    	 {
	    		
	    		for(String NewValue : LbTypeNewValue){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mappingsDao.updateMapping(new Mappings(val+1,InventoryName, Material, NewValue,"Null", "Null"));
	    			}
	    		for(String NewValue : LbClassOrGrade){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mappingsDao.updateMapping(new Mappings(val+1,InventoryName, Material, "Null",NewValue, "Null"));
	    			}
	    		for(String NewValue : LbCatogory){
	    			 int val = mappingsDao.getMaxId();  	
	    			    	mappingsDao.updateMapping(new Mappings(val+1,InventoryName, Material, "Null","Null", NewValue));
	    			}
	    		
	    	 }
	    	
	      }
		return decodedJson; 
	  	
	}
	
	
/*	private ArrayList<String> CompareHigherSizeOfList(int PiSize, ArrayList<String> ListOfType,ArrayList<String> ListOfClass, ArrayList<String> ListOfCatogory)
	{
		ArrayList<String> HigherList = new ArrayList<String>();
		
		if(PiSize == 0)
		{
		int LiListSize = ListOfType.size(); 
		int LiClassSize = ListOfClass.size();
		int LiCatogorySize = ListOfCatogory.size();
		
		int max = Math.max(Math.max(LiListSize,LiClassSize),LiCatogorySize);
		
		if(ListOfType.size() == max)
			HigherList=ListOfType;
		else if(ListOfClass.size() == max)
			HigherList=ListOfClass;
		else
			HigherList=ListOfCatogory;		
		}
		
		else
		{
			int LiListSize = ListOfType.size();
			int LiClassSize = ListOfClass.size();
			int LiCatogorySize = ListOfCatogory.size();
			
			if(LiListSize == PiSize)
				LiListSize =0;
			else if(LiClassSize == PiSize)
				LiClassSize= 0;
			else if(LiCatogorySize == PiSize)
				LiCatogorySize= 0;
			
			
			int max = Math.max(Math.max(LiListSize,LiClassSize),LiCatogorySize);
			
			if(ListOfType.size() == max)
				HigherList=ListOfType;
			else if(ListOfClass.size() == max)
				HigherList=ListOfClass;
			else
				HigherList=ListOfCatogory;		
			
		}
		
		return HigherList;
	}
	
*/	
	

	@RequestMapping(value = "updateValvesDetails", method = RequestMethod.POST)
	public @ResponseBody String updateValvesDetails(String[] model, String[] material, String[] end, String[] type, String[] pressureRatings,
			String[] sizeRange, String[] maxInletPressure, String[] operation, String[] seatAndSeals){
		for(int i = 0; i < model.length; i++){
			Valves valves = new Valves(i+1, model[i], material[i], end[i], type[i], pressureRatings[i], sizeRange[i], maxInletPressure[i], operation[i], seatAndSeals[i]);
			valvesDao.updateValves(valves);
		}
		
		return "true";
	}
	
}
