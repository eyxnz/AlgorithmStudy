class Solution {
    public int solution(int[][] sizes) {
        for(int i = 0; i < sizes.length; i++) {
            int w = sizes[i][0], h = sizes[i][1]; // 가로, 세로
            
            if(h > w) { // 큰 쪽을 가로로 설정
                sizes[i][0] = h;
                sizes[i][1] = w;
            }
        }
        
        int mw = 0, mh = 0;
        for(int i = 0; i < sizes.length; i++) {
            mw = Math.max(mw, sizes[i][0]);
            mh = Math.max(mh, sizes[i][1]);
        }
        
        int answer = mw * mh;
        return answer;
    }
}