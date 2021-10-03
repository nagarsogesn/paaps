package abcs;

import java.util.ArrayList;
import java.util.HashMap;	
import java.util.stream.IntStream;	
public class DataSet 
{	
		private String[][] data = null; 
		private Feature classFeature = null;	
		private HashMap<String, Double> priorProbs = new HashMap<String, Double>();	
		public DataSet(String[][] data) 
		{ 
				this.data =data; 
		}
		private DataSet calcPriorProbs() 
		{
			classFeature = new Feature(data, data[0].length-1);
			classFeature.getFeaturevalues().stream().forEach(featureValue ->
			priorProbs.put(featureValue.getName(), (double)featureValue.getOccurences() / (data.length-1)));
			return this;
		}
		public	HashMap<String, Double> calcCondProbs(HashMap<String, String> instance) 
		{
			calcPriorProbs();
			HashMap<String, Double> condProbs = new HashMap<String,	Double>();
			classFeature.getFeaturevalues().forEach(featureValue ->	{
			HashMap<String, String> logMap = new HashMap<String, String>();
			logMap.put(featureValue.getName(),featureValue.getOccurences() +"/"+ (data.length-1));
			DataSet newDataSet = createDataSet(classFeature.getFeatureValue(featureValue.getName()));
			double condProb = calcCondProb(newDataSet, featureValue.getName(), instance, logMap);
			condProbs.put(featureValue.getName(), condProb);
			System.out.println(getResultStr(newDataSet, instance, logMap, condProb, featureValue.getName()));
		});
	return condProbs;
	}		
 private double calcCondProb(DataSet newDataSet, String classFeatureValue, HashMap<String, String> instanceMap, HashMap<String, String> logMap) 
 {  
 	ArrayList<Feature> features=new ArrayList<Feature>();
	instanceMap.keySet().stream().forEach(featureName ->
	features.add(new Feature(newDataSet.data, getColNumb(featureName)).calcProb(instanceMap.get(featureName), logMap)));
 	double condProb = priorProbs.get(classFeatureValue);
	for (int i = 0; i < features.size(); i++) 
		{
		condProb *= features.get(i).getProbabilty(); 
		}
	return condProb;
}
private static String getResultStr(DataSet dataSet, HashMap<String,String> instanceMap, HashMap<String,String> logMap, double prob, String featureValue) 
{ 
		StringBuffer resultSB = new StringBuffer(dataSet+"\n");
		String instanceStr = getInstanceStr(dataSet, instanceMap);
resultSB.append("P("+featureValue+"|"+instanceStr+") = (P("+featureValue+")");
IntStream.range(0, dataSet.data[0].length-1).forEach(i -> resultSB.append("*P("+instanceMap.get(dataSet.data[0][i])+"|"+featureValue+")")); 
resultSB.append(") / "+"P("+instanceStr+")\n");
resultSB.append("P("+featureValue+"|" + instanceStr + ") = "+"(("+logMap.get(featureValue)+")");
IntStream.range(0, dataSet.data[0].length-1).forEach(i -> resultSB.append("*("+logMap.get(dataSet.data[0][i])+")")); 
resultSB.append(") / "+"P("+instanceStr+")\n");
resultSB.append("P("+featureValue+"|"+ instanceStr + ") = "+String.format("%.5f",prob)+" / "+"P(" + instanceStr + ") \n"); 
return resultSB.toString();
}
private DataSet createDataSet(FeatureValue classFeatureValue) 
{
//	System.out.println("classfeature:"+classFeatureValue.getOccurences()+1);
String returnData[][] = new String[classFeatureValue.getOccurences()+1][data[0].length]; 
returnData[0] = data[0]; 
//System.out.println("size of returndata: "+returnData.length);
int counter = 1;
//for(int i=0;i<returnData.length;i++)
//{
//System.out.println(returnData[i]);
//}
for (int row = 1; row < data.length; row++)
{
//	System.out.println("row: "+row+" counter: "+counter);
//	System.out.println("data[row]: "+data[row][data[0].length - 1]+" name: "+classFeatureValue.getName());
if (data[row][data[0].length - 1].equals(classFeatureValue.getName()))
{	
	returnData[counter] = data[row];
	counter++;
}
}
return new DataSet(returnData);
}

private int getColNumb(String colName) 
{ 
int returnValue = -1;
for (int column = 0; column < data[0].length; column++)
if (data[0][column] == colName) {returnValue = column; break;} 
return returnValue;
}
/*if (data[row][data[0].length - 1].equals(classFeatureValue.getName())) returnData[counter**] 
return new DataSet(returnData);
private int getColNurrb(String colName) { int returnValue * -1;
for (int column - 0; column < data[0].length; column**)
if (data[0][column] ** colName) {returnValue • column; break;} return returnValue;
}*/
static String getInstanceStr(DataSet dataSet, HashMap<String, String> instanceMap) 
{
StringBuffer instanceSB=new StringBuffer("<");
IntStream.range(0, dataSet.data[0].length-2).forEach(i -> instanceSB.append(instanceMap.get(dataSet.data[0][i])+", ")); 
return (instanceSB. append(instanceMap. get (dataSet. data [0] [dataSet .data [0] .length-2] )+">")).toString();
}

public String[][] getData() { return data; } 
public String toString() 
{
StringBuffer StringBuffer = new StringBuffer();
IntStream.range(0,data.length).forEach(row -> {
IntStream.range(0, data[row].length).forEach(column -> 
{ StringBuffer.append(data[row][column]);
IntStream.range(0, 24 - data[row][column].length()).forEach(i -> StringBuffer.append(" "));
});
StringBuffer.append("\n"); 
if (row == 0) {
IntStream.range(0, 108).forEach(i -> StringBuffer.append("-")); 
StringBuffer.append("\n");
}
});
return StringBuffer.toString();
}
}