package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
	private JSONObject jsonObj;
	private List<Individual> individualList;
	private List<Individual> inactiveMembers;
	private List<Individual> activeMembers;
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     * @throws  
     */
	public TeamsJsonReader() {
		// TODO Auto-generated constructor stub
		
		try
		{
		JSONParser parser=new JSONParser();
		FileReader reader=new FileReader(new File("D:\\eclipse\\assignment2\\src\\main\\resources\\db.json"));
		jsonObj=(JSONObject)parser.parse(reader);
		}
		catch(IOException |ParseException ex){
			ex.printStackTrace();
		}
		
	}
	
    public List<Individual> getListOfIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
    	
    		
     	individualList=new ArrayList<>();
    	JSONArray individualJsonArray=(JSONArray) jsonObj.get("individuals");    	
    	for(int i=0;i<individualJsonArray.size();i++){
    		JSONObject ob=(JSONObject)individualJsonArray.get(i);    		
    		Map<String,Object> map=(Map<String, Object>) ob.clone();
    		Individual individual=new Individual(map);
    		individualList.add(individual);   		
    	}
    	return individualList;
    	
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
        //throw new UnsupportedOperationException("Not implemented.");
        System.out.println(" hehehe"+id);
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.getId()==id.intValue()){
    		    			return individual;
    		}    			
    	}
    	throw new ObjectNotFoundException("object not found", "Id", id.toString());
    	    	
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
       // throw new UnsupportedOperationException("Not implemented.");
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.getName().equalsIgnoreCase(name)){
    			
    			return individual;
    			
    		}
    			
    	}
    	
    	throw new ObjectNotFoundException("object not found", "name" ,name);
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
        //throw new UnsupportedOperationException("Not implemented.");
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
         inactiveMembers=new ArrayList<>();
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(!(individual.isActive()))
    			inactiveMembers.add(individual);
    	}
    	return inactiveMembers;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
        //throw new UnsupportedOperationException("Not implemented.");
    	individualList=getListOfIndividuals();
    	Iterator<Individual>itr=individualList.iterator();
    	activeMembers=new ArrayList<>();
    	Individual individual=null;
    	while(itr.hasNext()){
    		 individual=itr.next();
    		if(individual.isActive())
    			activeMembers.add(individual);
    	}
    	return activeMembers;
    }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
       
    	//JSONObject jsonObj=JSONLoader();
    	List<Team> teamList=new ArrayList<>();
    	Team team=null;
    	JSONArray teamArray=(JSONArray)jsonObj.get("teams");
    	for(int i=0;i<teamArray.size();i++){
    	  JSONObject object=(JSONObject) teamArray.get(i);
    	  Map<String, Object> map=(Map<String, Object>) object.clone();    	 
    	  team=new Team(map);
    	  teamList.add(team);
    	}
    	return teamList;
    	    	 //throw new UnsupportedOperationException("Not implemented.");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /*private JSONObject JSONLoader()  {
	JSONObject jsonObj=null;
	try
	{
	JSONParser parser=new JSONParser();
	FileReader reader=new FileReader(new File("F:\\assignment-resource-io\\src\\test\\resources\\db.json"));
	jsonObj=(JSONObject)parser.parse(reader);
	}
	catch(IOException |ParseException ex){
		ex.printStackTrace();
	}
	return jsonObj;
}*/
}
