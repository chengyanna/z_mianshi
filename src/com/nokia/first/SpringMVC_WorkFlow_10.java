/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 23:09:03
 * 1.��˵һ��SpringMVC�Ĺ�������
 * ��������->DispatcherServlet(���������)->���ô�����ӳ����HandlerMapping�ҵ�������->����HandlerExecutionChain��handlerInterceptor��Handler��
 * ->ͨ�����������������þ���Ĵ�����->���ô�����->����ModelAndView->��ͼ����ViewResover->����view->��Ⱦ��ͼ->��Ӧ�û�
 * 
 * ����ģ�����ݷ�ʽһ���������ķ���ֵ����ΪModelAndView
 * @RequestMapping("/testModelAndView")
 * public ModelAndView testModelAndView(){
 * 	ModelAndView mv=new ModelAndView(); //1.����ModelAndView����
 *  mv.addObject("user","admin");//2.����ģ�����ݣ����ջ�ŵ�request����
 *  mv.setViewName("success");//3.������ͼ
 *  return mv; 
 * }
 * 
 * ����ģ�����ݷ�ʽ���������ķ���ֵ��Ȼ��String,�ڷ���������д���Map,Model����ModelMap
 * ���ܽ��������ķ���ֵ��ΪModelAndView�����ڷ���������д���Map,Model����ModelMap
 * SpringMVC����ת��Ϊһ��ModelAndView����
 * @RequestMapping("/testMap")
 * public String testMap(Map<String,Object> map){
 * 	map.put("user",new Employee()); //1.��Map�����ģ�����ݣ����ջ��Զ��ŵ�request����
 *  return "success";
 * }
 */
public class SpringMVC_WorkFlow_10 {

}
