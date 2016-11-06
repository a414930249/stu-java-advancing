package cn.hankchan.stu.pattern.factory;

public class FactoryProducer {

	public static AbstractFactory getFactory(String choice){
	      if(choice.equalsIgnoreCase("SHAPE")){
	         return new ShapeFactoryExtAbstract();
	      } else if(choice.equalsIgnoreCase("COLOR")){
	         return new ColorFactoryExtAbstract();
	      }
	      return null;
	   }
}
