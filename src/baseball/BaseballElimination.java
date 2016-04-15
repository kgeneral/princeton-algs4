import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/*
    In the baseball elimination problem, there is a division consisting of N teams.
    At some point during the season, team i has w[i] wins, l[i] losses, r[i] remaining games,
    and g[i][j] games left to play against team j. A team is mathematically eliminated -
    if it cannot possibly finish the season in (or tied for) first place.
    The goal is to determine exactly which teams are mathematically eliminated. For simplicity,
    we assume that no games end in a tie (as is the case in Major League Baseball) and that -
    there are no rainouts (i.e., every scheduled game is played).
 */
public class BaseballElimination {

    private int size;
    private Bag<String> teams;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {

        teams = new Bag<>();

        In baseballEliminationInput = new In(filename);
        while (baseballEliminationInput.hasNextLine()) {
            String line = baseballEliminationInput.readLine();
            String[] tokens = line.split("\\s+");
            if(tokens.length == 1) {
                size = Integer.parseInt(tokens[0]);
                continue;
            }

            String teamName = tokens[0];
            teams.add(teamName);
        }
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams;
    }

    // number of wins for given team
    public int wins(String team) {
        return 0;
    }

    // number of losses for given team
    public int losses(String team) {
        return 0;
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return 0;
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return 0;
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
