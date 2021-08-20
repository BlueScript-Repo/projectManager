package com.projectmanager.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.projectmanager.entity.*;
import com.projectmanager.model.ProductDetailsModel;
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
import com.projectmanager.dao.TaxesDao;
import com.projectmanager.dao.ValvesDao;
import com.projectmanager.model.InventoryMappingModel;
import com.projectmanager.model.InventoryValveModel;
import com.projectmanager.dao.ProductDefinitionDao;

@Controller
@EnableWebMvc
public class AddInventoryController {
	
	@Autowired
	MappingsDao mappingsDao;
	
	@Autowired
	ValvesDao valvesDao;
	
	@Autowired
	TaxesDao taxesDao;

	@Autowired
	ProductDefinitionDao productDefinitionDao;

	@RequestMapping(value = "/inventoryDetails", method = RequestMethod.GET)
	private ModelAndView inventoryDetails(){
		ModelAndView modelAndView = new ModelAndView();

		ArrayList<ProductDefinition> products = productDefinitionDao.getAllProductDefinition();

		StringBuffer mappingDetails = new StringBuffer();
		int indexToDisplay = 1;
		for(ProductDefinition definition : products)
		{
			mappingDetails.append("<tr class='lazy' >");
			mappingDetails.append("<td><input type='hidden' class='form-control' value='" + indexToDisplay + "' name='ItemId'  >" + indexToDisplay + "</td>");
			mappingDetails.append("<td><input type='checkbox' class='chkView' ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getProductId().getProduct() 				 + "' name='product'  ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getProductId().getMaterialOfConstruction() + "' name='materialOfConstruction' ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getProductId().getManufactureMethod() 		 + "' name='manufactureMethod'  ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getClassOrSch() 							 + "' name='classOrSch'  ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getMaterialSpecs() 						 + "' name='materialSpecs'  ></td>");
			mappingDetails.append("<td><input type='text'  class='form-control' value='" + definition.getStandardType() 						 + "' name='standardType'  ></td>");
			mappingDetails.append("</tr>");
			indexToDisplay++;

		}

	
		ArrayList<Valves> valvesData = valvesDao.getValveDetails();
		
		StringBuffer valvesDetails = new StringBuffer();
		int index2 = 1;
		for(Valves valves : valvesData){
			valvesDetails.append("<tr class='lazy'>");
			valvesDetails.append("<td><input type='hidden' class='form-control' value='" + index2 + "' name='ItemId'  >"+ index2 +"</td>");
			valvesDetails.append("<td><input type='checkbox' class='chkView' ></td>");
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

		ArrayList<TaxesEntity> taxesData = taxesDao.getTaxesDetails();
		
		StringBuffer taxesDetails = new StringBuffer();
		
		for (TaxesEntity taxes : taxesData){
			taxesDetails.append("<label style='font-weight: bold; padding-right: 20px;'>CGST :</label> <input type='text' id='cGst' name='cGst' value='" + taxes.getcGst()+ "' style='text-align:center; height: 40px;width: 70px;'><br><br>");
			taxesDetails.append("<label style='font-weight: bold; padding-right: 20px;'>SGST :</label> <input type='text' id='sGst' name='sGst' value='" + taxes.getsGst()+ "' style='text-align:center; height: 40px;width: 70px;'><br><br>");
			taxesDetails.append("<label style='font-weight: bold; padding-right: 25px;'>IGST :</label> <input type='text' id='iGst' name='iGst' value='" + taxes.getiGst()+ "' style='text-align:center; height: 40px;width: 70px;'><br><br>");
			
		}
		
		modelAndView.setViewName("InventoryDetails");
		modelAndView.addObject("mappingDetails", mappingDetails.toString());
		modelAndView.addObject("valvesDetails", valvesDetails.toString());
		modelAndView.addObject("taxesDetails", taxesDetails.toString());
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

	@PostMapping("/updateProductDetails")
	public @ResponseBody String updateProductDetails(@RequestBody String dataArrayToSend) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException
	{
		String decodedJson = java.net.URLDecoder.decode(dataArrayToSend, "UTF-8");
		ObjectMapper jacksonObjectMapper = new ObjectMapper(); // This is Jackson
		List<ProductDetailsModel> userRolesGUIBeans =  jacksonObjectMapper.readValue(decodedJson, new TypeReference<List<ProductDetailsModel>>(){});

		for(ProductDetailsModel productDetails: userRolesGUIBeans)
		{
			String product = productDetails.getProduct();
			String moc= productDetails.getMaterialOfConstruct();
			String constructType = productDetails.getConstructType();
			String materialSpecs = productDetails.getMaterialSpec();
			String classOrSch = productDetails.getClassOrSch();
			String standardType = productDetails.getStandardType();


			ArrayList<ProductDefinition> listOfProductDetails = productDefinitionDao.getProductDetails(product, moc,constructType);

			if(listOfProductDetails.size()> 0){

				for(ProductDefinition productDefinition :listOfProductDetails){
					String standardTypes = productDefinition.getStandardType();
					String classOrSchs = productDefinition.getClassOrSch();
					String materialSpec = productDefinition.getMaterialSpecs();

					String newForMaterialSpecs = materialSpec + "," + materialSpecs;
					String newFroClassOrSch =  classOrSchs+ "," + classOrSch;
					String newForStandaerdType = standardTypes+ ","+ standardType;

					productDefinitionDao.updateProductDefination(product,moc,constructType,newForMaterialSpecs,newFroClassOrSch,newForStandaerdType);
				}

			}else{

			ProductDefinition productDefinition = new ProductDefinition();
			productDefinition.setClassOrSch(productDetails.getClassOrSch());
			productDefinition.setMaterialSpecs(productDetails.getMaterialSpec());
			productDefinition.setStandardType(productDetails.getStandardType());
			productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));


			productDefinitionDao.saveProductDefinition(productDefinition);}
		}

		return decodedJson;
	}

//	@PostMapping("/updateMappingDetails")
//	public @ResponseBody String updateMappingDetails(@RequestBody String dataArrayToSend) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException{
//
//
//		String decodedJson = java.net.URLDecoder.decode(dataArrayToSend, "UTF-8");
//	      ObjectMapper jacksonObjectMapper = new ObjectMapper(); // This is Jackson
//	      List<ProductDetailsModel> userRolesGUIBeans =  jacksonObjectMapper.readValue(decodedJson, new TypeReference<List<ProductDetailsModel>>(){});
//
//
//	      for(ProductDetailsModel productDetails:userRolesGUIBeans)
//	      {
//	    	  ArrayList<String> ArrayOfMaterialSpec = new ArrayList<String>() ;
//	    	  ArrayList<String> ArrayOfClassOrSch = new ArrayList<String>();
//	    	  ArrayList<String> ArrayOfStandardType = new ArrayList<String>();
//
//	    	  String product = productDetails.getProduct();
//	    	  String moc = productDetails.getMaterialOfConstruct();
//	    	  String ct = productDetails.getConstructType();
//	    	  String classOrSch = productDetails.getClassOrSch();
//	    	  String ms = productDetails.getMaterialSpec();
//			  String standardType =  productDetails.getStandardType();
//
//	    	  ArrayList<ProductDefinition> listOfProductDetails = productDefinitionDao.getProductDetails(product, moc,ct);
//
//
//	    	  for(ProductDefinition MappData:listOfProductDetails)
//
//	    	  {
//	    		  ArrayOfMaterialSpec.add(MappData.getMaterialSpecs());
//	    		  ArrayOfClassOrSch.add(MappData.getClassOrSch());
//	    		  ArrayOfStandardType.add(MappData.getStandardType());
//	    	  }
//
//
//	    	  ArrayList<String> LbmsNewValue = CheckValid(ms,ArrayOfMaterialSpec.toString().replace("[","").replace("]", ""));
//	    	  ArrayList<String> LbClassOrSch = CheckValid(classOrSch,ArrayOfClassOrSch.toString().replace("[","").replace("]", ""));
//	    	  ArrayList<String> LbStandardType = CheckValid(standardType,ArrayOfStandardType.toString().replace("[","").replace("]", ""));
//
//	    	  if(LbmsNewValue.size()==0 && LbClassOrSch.size()==0 && LbStandardType.size()==0)
//		    	 {
//					 ProductDefinition productDefinition = null;
//
//		    			 int val = productDefinitionDao.getMaxId();
//					 productDefinition = new ProductDefinition(product, moc, ct, "Null","Null", "Null");
//					 productDefinitionDao.saveProductDefinition(productDefinition);
//
//		    	 }
//	    	  else if(LbmsNewValue.size()>0 && LbClassOrSch.size()==0 && LbStandardType.size()==0)
//	    	 {
//				 ProductDefinition productDefinition = null;
//	    			for(String NewValue : LbmsNewValue){
//						int val = productDefinitionDao.getMaxId();
//						productDefinition = new ProductDefinition(product, moc, ct, NewValue,"Null", "Null");
//						productDefinitionDao.saveProductDefinition(productDefinition);
//	    			}
//
//	    	 }
//
//	    	 else if(LbmsNewValue.size()==0 && LbClassOrSch.size()>0 && LbStandardType.size()==0)
//	    	 {
//				 ProductDefinition productDefinition = null;
//	    			for(String NewValue : LbClassOrSch){
//						int val = productDefinitionDao.getMaxId();
//						productDefinition = new ProductDefinition(product, moc, ct,"Null",NewValue, "Null");
//						productDefinitionDao.saveProductDefinition(productDefinition);
//	    			}
//
//	    	 }
//
//	    	 else if(LbmsNewValue.size()==0 && LbClassOrSch.size()==0 && LbStandardType.size()>0)
//	    	 {
//				 ProductDefinition productDefinition = null;
//	    			for(String NewValue : LbStandardType){
//						int val = productDefinitionDao.getMaxId();
//						productDefinition = new ProductDefinition(product, moc, ct,"Null","Null", NewValue);
//						productDefinitionDao.saveProductDefinition(productDefinition);
//	    			}
//
//	    	 }
//
//
//	    	 else if(LbmsNewValue.size()>0 && LbClassOrSch.size()>0 && LbStandardType.size()==0)
//	    	 {
//
//	    			if(LbmsNewValue.size()>LbClassOrSch.size() ||LbmsNewValue.size()==LbClassOrSch.size())
//	    		    {
//	    		    	for(String NewType : LbmsNewValue){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewClass : LbClassOrSch)
//	    		    		{
//
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs(NewType);
//								productDefinition.setClassOrSch(NewClass);
//								productDefinition.setStandardType("Null");
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    		    else if(LbClassOrSch.size()>LbmsNewValue.size())
//	    		    {
//	    		    	for(String NewClass  : LbClassOrSch ){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewType  : LbmsNewValue)
//	    		    		{
//
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs(NewType);
//								productDefinition.setClassOrSch(NewClass);
//								productDefinition.setStandardType("Null");
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    	 }
//
//	    	 else if(LbmsNewValue.size()==0 && LbClassOrSch.size()>0 && LbStandardType.size()>0)
//	    	 {
//
//	    		    if(LbClassOrSch.size()>LbStandardType.size() ||LbClassOrSch.size()==LbStandardType.size())
//	    		    {
//	    		    	for(String NewClass : LbClassOrSch){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewCatogory : LbStandardType)
//	    		    		{
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs("Null");
//								productDefinition.setClassOrSch(NewClass);
//								productDefinition.setStandardType(NewCatogory);
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    		    else if(LbStandardType.size()>LbClassOrSch.size())
//	    		    {
//	    		    	for(String NewCatogory  : LbStandardType ){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewClass  : LbClassOrSch)
//	    		    		{
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs("Null");
//								productDefinition.setClassOrSch(NewClass);
//								productDefinition.setStandardType(NewCatogory);
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    	 }
//
//	    	 else if(LbmsNewValue.size()>0 && LbClassOrSch.size()==0 && LbStandardType.size()>0)
//	    	 {
//
//	    		    if(LbmsNewValue.size()>LbStandardType.size() ||LbmsNewValue.size()==LbStandardType.size())
//	    		    {
//	    		    	for(String NewType : LbmsNewValue){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewCatogory : LbStandardType)
//	    		    		{
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs(NewType);
//								productDefinition.setClassOrSch("Null");
//								productDefinition.setStandardType(NewCatogory);
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    		    else if(LbStandardType.size()>LbmsNewValue.size())
//	    		    {
//	    		    	for(String NewCatogory  : LbStandardType ){
//							ProductDefinition productDefinition = new ProductDefinition();
//	    		    		for(String NewType  : LbmsNewValue)
//	    		    		{
//								productDefinition.setProductId(new ProductId(productDetails.getProduct(), productDetails.getMaterialOfConstruct(), productDetails.getConstructType()));
//								productDefinition.setMaterialSpecs(NewType);
//								productDefinition.setClassOrSch("Null");
//								productDefinition.setStandardType(NewCatogory);
//								productDefinitionDao.saveProductDefinition(productDefinition);
//								productDefinition = new ProductDefinition();
//	    		    		}
//
//	    		    	}
//	    		    }
//	    	 }
//
//	    	else if(LbmsNewValue.size()>0 && LbClassOrSch.size()>0 && LbStandardType.size()>0)
//	    	 {
//
//	    		for(String NewValue : LbmsNewValue){
//					int val = productDefinitionDao.getMaxId();
//					productDefinitionDao.saveProductDefinition(new ProductDefinition(product,moc, ct, NewValue,"Null", "Null"));
//	    			}
//	    		for(String NewValue : LbClassOrSch){
//					int val = productDefinitionDao.getMaxId();
//					productDefinitionDao.saveProductDefinition(new ProductDefinition(product,moc, ct, "Null",NewValue, "Null"));
//	    			}
//	    		for(String NewValue : LbStandardType){
//					int val = productDefinitionDao.getMaxId();
//					productDefinitionDao.saveProductDefinition(new ProductDefinition(product,moc, ct, "Null","Null", NewValue));
//	    			}
//
//	    	 }
//
//	      }
//		return decodedJson;
//
//	}


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
	public @ResponseBody String updateValvesDetails(@RequestBody String dataArrayToSendContoller) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException{
		
		
		String decodedJson = java.net.URLDecoder.decode(dataArrayToSendContoller, "UTF-8");
	      ObjectMapper jacksonObjectMapper = new ObjectMapper(); // This is Jackson
	      List<InventoryValveModel> userRolesGUIBeans =  jacksonObjectMapper.readValue(decodedJson, new TypeReference<List<InventoryValveModel>>(){});
	      
	      for(InventoryValveModel inventoryValve :userRolesGUIBeans){
	    	 String itemid =  inventoryValve.getItemId();
	    	 String Model = inventoryValve.getModel();
	    	 String Material = inventoryValve.getMaterial();
	    	 String End = inventoryValve.getEnd();
	    	 String Type = inventoryValve.getType();
	    	 String PressureRatings = inventoryValve.getPressureRatings();
	    	 String SizeRange = inventoryValve.getSizeRange();
	    	 String MaxInletPressure = inventoryValve.getMaxInletPressure();
	    	 String Operation = inventoryValve.getOperation();
	    	 String SeatAndSeals = inventoryValve.getSeatAndSeals();
	    	 int itemInt = Integer.parseInt(itemid);
	    	 Valves valves = new Valves(itemInt, Model, Material, End, Type, PressureRatings, SizeRange, MaxInletPressure, Operation, SeatAndSeals);
				valvesDao.updateValves(valves);
	      }

		System.out.println(userRolesGUIBeans);
		
		return "true";
	}
	

	@RequestMapping(value = "updateTaxesDetails", method = RequestMethod.POST)
	public @ResponseBody String updateTaxesDetails(int cGst, int sGst, int iGst){
	
			//TaxesEntity taxes = new TaxesEntity();
		
			taxesDao.updateTaxes(cGst, sGst, iGst);

		
		return "true";
	}
	
}
