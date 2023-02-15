package spring2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//상대경로 불러오는 게 더 쉽다.z
		Resource resource=new ClassPathResource("/spring2/initContext.xml");
		//2.빈즈공장을 불러와서 객체를 얻어오기//xml정보->메모리에 저장
		BeanFactory factory=new XmlBeanFactory(resource);//Ctrl+Shift+o
		//3.factory에서 getBean("불러올 객체를 구분해서 가져올 id값");
		
		//인터페이스 객체를 통해서 변경시킬 클래스 객체를 구분해서 가져올 수 있다.
		//Message2->Message1으로 바꾸는 걸 xml에서 class값만 바꿔서 바꿀 수 있다.
		MessageBeanDI bean=(MessageBeanDI)factory.getBean("mBean");
		System.out.println("bean=>"+bean);
		bean.sayHello();
		

	}

}
