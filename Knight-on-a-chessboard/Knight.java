import java.lang.Math;
public class Knight {
    // initializing the matrix.  
    static int dp[][] = new int[8][8];
    
    public static int getMinSteps(int x, int y, int tx, int ty){
        if (x == tx && y == ty) { // if src = des
            return dp[0][0]; 
        } else if (dp[ Math.abs(x - tx)][ Math.abs(y - ty)] != 0) { // if value exists
            return dp[ Math.abs(x - tx)][ Math.abs(y - ty)]; 
        } else {  // if val doesn't exist
            int x1, y1, x2, y2; 
            // 4 possible steps a knight can take
            if (x <= tx) { 
                if (y <= ty) { 
                    x1 = x + 2; 
                    y1 = y + 1; 
                    x2 = x + 1; 
                    y2 = y + 2; 
                } else { 
                    x1 = x + 2; 
                    y1 = y - 1; 
                    x2 = x + 1; 
                    y2 = y - 2; 
                } 
            } else {
                if (y <= ty) { 
                    x1 = x - 2; 
                    y1 = y + 1; 
                    x2 = x - 1; 
                    y2 = y + 2; 
                } else { 
                    x1 = x - 2; 
                    y1 = y - 1; 
                    x2 = x - 1; 
                    y2 = y - 2; 
                }
            } 
            // minimum of the possible steps
            dp[ Math.abs(x - tx)][ Math.abs(y - ty)] = Math.min(getMinSteps(x1,y1, tx, ty),getMinSteps(x2, y2, tx, ty)) + 1;   
            dp[ Math.abs(y - ty)][ Math.abs(x - tx)] = dp[ Math.abs(x - tx)][ Math.abs(y - ty)]; 
            return dp[ Math.abs(x - tx)][ Math.abs(y - ty)]; 
        } 
    }

    static int solution(int src, int dest) { 
        int x,y,tx,ty;
        //convert into 2d
        x = src/8;
        y = src%8;
        tx = dest/8;
        ty = dest%8;
        int n = 7; 
        //corners and their diagonals (edge cases)
        if ((x == 0 && y == 0 && tx == 1 && ty == 1) //1 and 9 OR 0,0 and 1,1 (top left corner)
                || (x == 1 && y == 1 && tx == 0 && ty == 0)) { //9 and 1 OR 1,1 and 0,0
            return 4; 
        } else if ((x == 0 && y == n && tx == 1 && ty == n - 1) //7 and 14 OR 0,7 and 1,6 (top right corner)
                || (x == 1 && y == n - 1 && tx == 0 && ty == n)) { //14 and 7 OR 1,6 and 0,7
            return 4; 
        } else if ((x == n && y == 0 && tx == n - 1 && ty == 1) // 56 and 49 OR 7,0 and 6,1 (bottom left corner)
                || (x == n - 1 && y == 1 && tx == n && ty == 0)) { //  49 and 56 OR 6,1 and 7,0
            return 4; 
        } else if ((x == n && y == n && tx == n - 1 && ty == n - 1) // 63 and 54 OR 7,7 and 6,6(bottom right corner)
                || (x == n - 1 && y == n - 1 && tx == n && ty == n)) { //54 and 63 OR 6,6 and 7,7
            return 4; 
        } else { //Main solution(default values)
            dp[1][0] = 3; 
            dp[0][1] = 3; 
            dp[1][1] = 2; 
            dp[2][0] = 2; 
            dp[0][2] = 2; 
            dp[2][1] = 1; 
            dp[1][2] = 1;
            return getMinSteps(x,y,tx,ty);
        }
    } 
  
// Driver Code  
    static public void main(String[] args) { 
        int ans;  
        int src =0;
        int dest =1;
  
        ans = solution(src, dest);  
  
        System.out.println(ans); 
    } 
}