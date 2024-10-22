package program2;

public class test {
    public static void main(String[] args) {
        String word =  "   Vishwa Patil  ";
        StringBuilder sb =new StringBuilder(word);
    //     int leadingPointer=0;
    //     int trailingPointer= word.length();
    //    boolean isSpace = true ;
    //    for (Character ch : word.toCharArray()) {
    //       if (isSpace && ch ==' ') {
    //         continue;
    //       }
    //       isSpace =false;
    //       leadingPointer++;
    //    }

    //    isSpace=true;

    //    while (isSpace && word.toCharArray()) {
        
    //    }
    char[] ch= word.toCharArray();
    for (int index = 0; index < ch.length-1; index++) {
        if (index == 0 && ch[index]!=' ') {
            sb.append(ch[index]);
        }else if(index == ch.length-2&& ch[index-1]!= ' '){
             sb.append(ch[index]);
        }else{
            if (index==0) {
                continue;
            }
            if (ch[index]== ' ' && (ch[index-1] ==' ' || ch[index+1] == ' ')) {
                continue;
              }else{
                  sb.append(ch[index]);
              }
        }
       
    }
    
    
System.out.println(sb.toString());
 
    }
}
