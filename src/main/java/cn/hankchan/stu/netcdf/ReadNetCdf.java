package cn.hankchan.stu.netcdf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import ucar.ma2.Array;
import ucar.ma2.DataType;
import ucar.nc2.Dimension;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class ReadNetCdf {
	
	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {  
        String filename = "D:/NPData/guangdong/grapes/2016102100/rain.nc";  
        NetcdfFile ncfile = null;  
        try {  
            ncfile = NetcdfFile.open(filename);  
              
            //read dimensions  
            List<Dimension> list =  ncfile.getDimensions();  
            for(Dimension d : list){  
                System.out.println("name="+d.getName()+" length="+d.getLength());  
            }  
            //read variables  
            List<Variable> variables = ncfile.getVariables();  
            System.out.println();  
            for(Variable v : variables){
                System.out.println("name="+v.getName()+" NameAndDimension="+v.getNameAndDimensions()+" ElementSize="+v.getElementSize());  
                Array array = v.read();
                System.out.println(array.getDataAsByteBuffer());
            }
              
        } catch (IOException ioe) {  
        } finally {  
            if (null != ncfile)  
                try {  
                    ncfile.close();  
                } catch (IOException ioe) {  
                }  
        }  
    }
}
