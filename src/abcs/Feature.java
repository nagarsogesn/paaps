package abcs;

import java.util.HashSet;
import java.util.stream.IntStream;
import java.util.*;
import java.util.Iterator;

public class Feature 
{
	private String name=null;
	private String[][]data=null;
	private HashSet<FeatureValue> featureValues=new HashSet<FeatureValue>();
	private double probabilty;
	public Feature(String[][]data,int column)
	{
		this.name=data[0][column];
		this.data=data;
		IntStream.range(1,data.length).forEach(row ->featureValues.add(new FeatureValue(data[row][column])));
		featureValues.stream().forEach(featureValue ->
		{
			int counter=0;
			for(int row=1;row<data.length;row++)
			{
				
				if(featureValue.getName().equals(data[row][column])) 
				{
					featureValue.setOccurences(++counter);
//				System.out.println("from feature class: setoccur count:"+counter);
				}
			}
		});
	}
	
	public Feature calcProb(String featureValueName,HashMap<String,String>logMap)
	{
		if(getFeatureValue(featureValueName)!=null)
		{
			probabilty=(((double)getFeatureValue(featureValueName).getOccurences())/(data.length-1));
			logMap.put(this.name,getFeatureValue(featureValueName).getOccurences()+"/"+(data.length-1));
		}
		else
		{
			probabilty=0;
			logMap.put(this.name,"0/"+(data.length-1));
		}
		return this;
	}
	
	public FeatureValue getFeatureValue(String featureValueName)
	{
		FeatureValue returnValue=null;
		Iterator<FeatureValue> iterator=featureValues.iterator();
		while(iterator.hasNext())
		{
			FeatureValue featureValue=iterator.next();
			if(featureValue.getName().equals(featureValueName))
			{
				returnValue=featureValue;
				break;
			}
		}
		return returnValue;
	}
	public double getProbabilty()
	{
		return probabilty;
	}
	public String getName()
	{
		return name;
	}
	public HashSet<FeatureValue> getFeaturevalues()
	{
		return featureValues;
	}
	public String toString()
	{
		return name;
	}
}
