import java.util.*;
public class Election {
    private int totalVotes;
    private int currentVotes;
    private Map<String, Integer> voteCount;

    public Election(int totalVotes) {
        this.totalVotes = totalVotes;
        this.currentVotes = 0;
        this.voteCount = new HashMap<>();
    }

    public void initializeCandidates(LinkedList<String> candidates){
        for (String candidate: candidates){
            voteCount.put(candidate, 0);
        }
    }

    public void castVote(String candidate){
        if (!voteCount.containsKey(candidate) || currentVotes >= totalVotes){
            return;
        }
        voteCount.put(candidate, voteCount.get(candidate) + 1);
        currentVotes++;
    }

    public void castRandomVote(){
        if (currentVotes >= totalVotes){
            return;
        }
        Random random = new Random();
        List<String> candidateList = new ArrayList<>(voteCount.keySet());
        String randomCandidate = candidateList.get(random.nextInt(candidateList.size()));

        castVote(randomCandidate);
    }

    public void rigElection(String candidate){
        if (!voteCount.containsKey(candidate)){
            return;
        }

        for (String c : voteCount.keySet()) {
            voteCount.put(c, 0);
        }

        int riggedVotes = totalVotes / 2 + 1;
        voteCount.put(candidate, riggedVotes);

        int votesLeft = totalVotes - riggedVotes;
        for (String c : voteCount.keySet()) {
            if (!c.equals(candidate) && votesLeft > 0) {
                voteCount.put(c, 1);
                votesLeft--;
            }
        }

        currentVotes = totalVotes;
    }

    public List<String> getTopKCandidates(int k){
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        heap.addAll(voteCount.entrySet());

        List<String> topK = new ArrayList<>();
        while (k-- > 0 && !heap.isEmpty()) {
            topK.add(heap.poll().getKey());
        }
        return topK;
    }

    public void auditElection(){
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        heap.addAll(voteCount.entrySet());

        while (!heap.isEmpty()) {
            Map.Entry<String, Integer> entry = heap.poll();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
