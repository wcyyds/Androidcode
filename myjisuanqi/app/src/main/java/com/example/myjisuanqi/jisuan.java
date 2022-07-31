package com.example.myjisuanqi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class jisuan {
    //将中缀表达式转化成后缀表达式
    public static ArrayList a(StringBuffer sb){
        if(sb.charAt(0) == '-'){
            sb.insert(0,0);
        }
        //先把负数的东西转化成减法的东西
        for (int i = 1; i < sb.length(); i++){
            if(sb.charAt(i) == '-' && sb.charAt(i-1) == '('){
                sb.insert(i, 0);
            }
        }
        //这个就是把中缀表达式的东西存储在数组链表中
        ArrayList<String> result = new ArrayList<>();
        StringBuffer zhuan = new StringBuffer();
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == '.' || (sb.charAt(i) <= '9' && sb.charAt(i) >= '0')){
                zhuan.append(sb.charAt(i));
            }else{
                if(zhuan.length() != 0){
                    result.add(String.valueOf(zhuan));
                }
                zhuan.delete(0,zhuan.length());
                result.add(String.valueOf(sb.charAt(i)));
            }
        }
        //如果最后是一个数子，然后就没有穿到result的数组链表中去，所以最后加一个进去
        if(zhuan.length() != 0){
            result.add(String.valueOf(zhuan));
        }
        //中缀开始转后缀了
        Stack<String> stack = new Stack<>();
        ArrayList<String> returns = new ArrayList<>();
        for(int i = 0; i < result.size(); i++){
            String c = result.get(i);
           if(c.equals("-") || c.equals("+")){
               a1(stack, returns, c, 1);
           }else if (c.equals("*") || c.equals("/")){
               a1(stack, returns, c, 2);
           }else if(c.equals("(")){
               stack.push(c);
           }else if(c.equals(")")){
               a2(stack, returns);
           }else{
               returns.add(c);
           }
        }
        while(!stack.isEmpty()){
            returns.add(stack.pop());
        }
        return returns;
    }

    /**
     * 按级别处理操作符
     * @param stack
     * @param returns
     * @param c
     * @param ji
     */
    public static void a1(Stack stack, ArrayList returns,String c, int ji){
        while(!stack.isEmpty()){
            String top = (String) stack.pop();
            if(top.equals("(")){
                stack.push(top);
                break;
            }else{
                int topji = 0;
                if(top.equals("+") || top.equals("-")){
                    topji = 1;
                }else {
                    topji = 2;
                }

                if(topji < ji){
                    stack.push(top);
                    break;
                }else{
                    returns.add(top);
                }
            }
        }
        stack.push(c);
    }

    /**
     * 处理右括号的情况，一直弹出，直到遇到（为止
     * @param stack
     * @param returns
     */
    public static void a2(Stack stack, ArrayList returns){
        while (!stack.isEmpty()){
            String top = (String) stack.pop();
            if(top.equals("(")){
                break;
            }else{
                returns.add(top);
            }
        }
    }

    /**
     * 这个就是力扣的题，逆波兰表达式求值
     * @param returns
     * @return
     */
    public static BigDecimal b(ArrayList returns) {
        int n = returns.size();
        BigDecimal stack[] = new BigDecimal[n];
        int top = -1;
        for(int i = 0;i < n; i++){
            String del = (String) returns.get(i);
            switch(del){
                case"-":
                    top--;
                    stack[top] = stack[top].subtract(stack[top + 1]);
                    break;
                case"+":
                    top--;
                    stack[top] = stack[top].add(stack[top + 1]);
                    break;
                case"*":
                    top--;
                    stack[top] = stack[top].multiply(stack[top + 1]);
                    break;
                case"/":
                    top--;
                    stack[top] = stack[top].divide(stack[top + 1],9,BigDecimal.ROUND_HALF_UP);
                    break;
                default:
                    top++;
                    stack[top] = new BigDecimal(del);
            }
        }
        return stack[top];
    }

    //这个是调用上面的函数，简洁主文件代码
    public static String end(StringBuffer sb){
        jisuan wanle = new jisuan();
        ArrayList ending = new ArrayList();
        ending = a(sb);
        BigDecimal ending2 = b(ending);
        return String.valueOf(ending2);
    }

}
