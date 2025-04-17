import java.util.*;

public class ElectionSystem {
    public static void main(String[] args) {
        LinkedList<String> candidates = new LinkedList<>(Arrays.asList(
                "Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud"));
        int p = 5;

        Election election = new Election(p);
        election.initializeCandidates(candidates);

        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        System.out.println("Top 3 candidates after 5 votes: " + election.getTopKCandidates(3));

        election.rigElection("Marcus Fenix");

        System.out.println("Top 3 candidates after rigging the election: " + election.getTopKCandidates(3));
        System.out.println("Audit:");
        election.auditElection();
    }
}
