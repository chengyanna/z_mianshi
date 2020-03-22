/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 23:09:03
 * 1.简单说一下SpringMVC的工作流程
 * 发送请求->DispatcherServlet(中央控制器)->调用处理器映射器HandlerMapping找到处理器->返回HandlerExecutionChain（handlerInterceptor和Handler）
 * ->通过处理器适配器调用具体的处理器->调用处理器->返回ModelAndView->视图解析ViewResover->返回view->渲染视图->响应用户
 * 
 * 处理模型数据方式一：将方法的返回值设置为ModelAndView
 * @RequestMapping("/testModelAndView")
 * public ModelAndView testModelAndView(){
 * 	ModelAndView mv=new ModelAndView(); //1.创建ModelAndView对象
 *  mv.addObject("user","admin");//2.设置模型数据，最终会放到request域中
 *  mv.setViewName("success");//3.设置视图
 *  return mv; 
 * }
 * 
 * 处理模型数据方式二：方法的返回值仍然是String,在方法的入参中传入Map,Model或者ModelMap
 * 不管将处理器的返回值设为ModelAndView还是在方法的入参中传入Map,Model或者ModelMap
 * SpringMVC都会转换为一个ModelAndView对象
 * @RequestMapping("/testMap")
 * public String testMap(Map<String,Object> map){
 * 	map.put("user",new Employee()); //1.向Map中添加模型数据，最终会自动放到request域中
 *  return "success";
 * }
 */
public class SpringMVC_WorkFlow_10 {

}
