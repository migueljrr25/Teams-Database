import java.io.*;
import java.util.*;
public class P1
{
   /* Define data structures for holding the data here */
   ArrayList<Coach> coaches;
   ArrayList<Team> teams;  
  
   public P1()
   {
       /* initialize the data structures */
       coaches = new ArrayList<Coach>();
       teams = new ArrayList<Team>();
   }

   public void run()
   {
       CommandParser parser = new CommandParser();
       System.out.println("The mini-DB of NBA coaches and teams");
       System.out.println("Please enter a command. Enter 'help' for a list of commands.");
       System.out.println();
       System.out.print("> ");
       Command cmd = null;
       while((cmd = parser.fetchCommand()) != null)
       {          
           if(cmd.getCommand().equals("help")){doHelp();}
           
           else if(cmd.getCommand().equals("add_coach")){
               String[] addingCoubyarg = cmd.getParameters();
               Boolean found = false;
               int j = 0;              
               Coach coachHoldat = new Coach(addingCoubyarg[0], Integer.parseInt(addingCoubyarg[1]), addingCoubyarg[2], addingCoubyarg[3], Integer.parseInt(addingCoubyarg[4]), Integer.parseInt(addingCoubyarg[5]), Integer.parseInt(addingCoubyarg[6]), Integer.parseInt(addingCoubyarg[7]), addingCoubyarg[8]);
               coaches.add(coachHoldat);              
           }
           else if(cmd.getCommand().equals("add_team")){
               String[] argsaddingteam = cmd.getParameters();
               Boolean found = false;
               int j = 0;             
               Team teamholdat = new Team(argsaddingteam[0], argsaddingteam[1], argsaddingteam[2], argsaddingteam[3]);
               teams.add(teamholdat);
           }
           else if(cmd.getCommand().equals("print_coaches"))
           {int j = 0; while(j<coaches.size()){System.out.println(coaches.get(j));j++;}}
           
           else if(cmd.getCommand().equals("print_teams"))
           {int j = 0; while(j<teams.size()){System.out.println(teams.get(j));j++;}}

           else if(cmd.getCommand().equals("coaches_by_name"))
           {
               String coachesbynamep[] = cmd.getParameters();
               Boolean found = false;
               int j = 0; 
               while(j<teams.size()){j++;
                   if (coaches.get(j).getLast_name().trim().equalsIgnoreCase(coachesbynamep[0].replace("+", " "))){
                       System.out.println(coaches.get(j)); 
                       found = true;}
               }
               if(found == false){System.out.println("No match for the criteria searched, please try again");}
           }

           else if(cmd.getCommand().equals("teams_by_city"))
           {
               String teams_bycityP[] = cmd.getParameters();
               Boolean found = false;
               int counter = 0;
               int j = 0;
               while(j<teams.size()){j++;
                        counter++;
                   if (teams.get(j).getLocation().trim().equals(teams_bycityP[0]))
                   {
                       System.out.println(teams.get(j));
                       found = true;
                   }
               }
            if(found == false){System.out.println("No match for the criteria searched, please try again");}
           }

           else if(cmd.getCommand().equals("load_coaches"))
           {
               String argsaddingteam[] = cmd.getParameters();              
               Scanner file;
               try
               {
                   file = new Scanner(new File(argsaddingteam[0]));
                   String line_by_line;
                   String[] vls;
                   if(file.hasNextLine())
                       line_by_line = file.nextLine();               
                   while (file.hasNextLine())
                   {
                       line_by_line = file.nextLine();
                       vls = line_by_line.split(",");
                       if (vls.length == 9)
                       {
                           Coach coachHoldat = new Coach(vls[0], Integer.parseInt(vls[1]), vls[2], vls[3], Integer.parseInt(vls[4]), Integer.parseInt(vls[5]), Integer.parseInt(vls[6]), Integer.parseInt(vls[7]), vls[8]);
                           coaches.add(coachHoldat);                          
                       }
                   }
                   file.close();      
               }
               catch(FileNotFoundException Exception)
               {
                   System.out.println(argsaddingteam[0] + " file not found, please try again");
               }  
           }
           else if(cmd.getCommand().equals("load_teams"))
           {
               String argsaddingteam[] = cmd.getParameters();
               Scanner file;
               try
               {
                   file = new Scanner(new File(argsaddingteam[0]));
                   String line_by_line;
                   String[] vls;
                   if(file.hasNextLine())
                       line_by_line = file.nextLine();        
                   while (file.hasNextLine())
                   {
                       line_by_line = file.nextLine();
                       vls = line_by_line.split(",");
                       if (vls.length == 4)
                       {
                           Team teamholdat = new Team(vls[0], vls[1], vls[2], vls[3]);
                           teams.add(teamholdat);                          
                       }
                   }
                   file.close();  
               }
               catch(FileNotFoundException Exception)
               {
                   System.out.println(argsaddingteam[0] + " file does not exist!");
               }                  
           }
           else if(cmd.getCommand().equals("best_coach"))
           {
               String argsaddingteam[] = cmd.getParameters();
               int coachwithmostwins = 0;
               int counter = 0;
               int totalwins;
               Boolean flag;
               Coach coachHoldat;
              int j= 0;
               while(j<coaches.size()){j++;
                   coachHoldat = coaches.get(j);
                   if(coachHoldat.getYear() == Integer.parseInt(argsaddingteam[0]))
                   {
                       totalwins = (coachHoldat.getSeason_win() - coachHoldat.getSeason_loss()) + (coachHoldat.getPlayoff_win() - coachHoldat.getPlayoff_loss());
                       if(totalwins > coachwithmostwins)
                       {
                           coachwithmostwins = totalwins;
                       }
                   }
                   counter++;
               }
              int k= 0;
               while(j<coaches.size()){k++;
                   coachHoldat = coaches.get(k);
                   if(coachHoldat.getYear() == Integer.parseInt(argsaddingteam[0]))
                   {
                       totalwins = (coachHoldat.getSeason_win() - coachHoldat.getSeason_loss()) + (coachHoldat.getPlayoff_win() - coachHoldat.getPlayoff_loss());
                  
                       if(totalwins == coachwithmostwins)
                       {
                           System.out.println(coachHoldat.getFirst_name() + " " + coachHoldat.getLast_name());
                       }
                   }
               }
           }
           else if(cmd.getCommand().equals("search_coaches"))
           {
               String[] argsaddingteam = cmd.getParameters();
               String[] tokens = argsaddingteam[0].split("=");
               String field = tokens[0];
               String value = tokens[1];
              // System.out.println(field + " " + value);
               //System.out.println(argsaddingteam[1]);
                //for(int j=0; j<argsaddingteam.length;j++)
               for(int i = 0; i < coaches.size(); i++)
               {
                   Coach coachHoldat = coaches.get(i);           
                   if(field.equalsIgnoreCase("coach_id") && value.equalsIgnoreCase(coachHoldat.getCoach_ID()))
                           {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("year") && Integer.parseInt(value) == coachHoldat.getYear())
                            {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("first_name") && value.equalsIgnoreCase(coachHoldat.getFirst_name()))
                            {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("last_name") && value.equalsIgnoreCase(coachHoldat.getLast_name()))
                            {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("season_win") && Integer.parseInt(value) == coachHoldat.getSeason_win())
                            {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("season_loss") && Integer.parseInt(value) == coachHoldat.getSeason_loss())
                            {System.out.println(coachHoldat);}               
                   if(field.equalsIgnoreCase("playoff_win") && Integer.parseInt(value) == coachHoldat.getPlayoff_win())
                            {System.out.println(coachHoldat);}              
                   if(field.equalsIgnoreCase("playoff_loss") && Integer.parseInt(value) == coachHoldat.getPlayoff_loss())
                            {System.out.println(coachHoldat);}
                   if(field.equalsIgnoreCase("team") && value.equalsIgnoreCase(coachHoldat.getTeam()))
                            {System.out.println(coachHoldat);}
               }
           }
           else if(cmd.getCommand().equals("exit"))
           {
               System.out.println("Leaving the database, goodbye!");
               break;
           }
           else if(cmd.getCommand().equals(""))
           {

           }
           else
           {
               System.out.println("Invalid Command, please try again!");
           }

           System.out.print("> ");
       }

   }

   private void doHelp()
   {
       System.out.println("add_coach ID SEASON SEASON_ORDER FIRST_NAME LAST_NAME SEASON_WIN ");
       System.out.println("SEASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
       System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
       System.out.println("print_coaches - print a listing of all coaches");
       System.out.println("print_teams - print a listing of all teams");
       System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
       System.out.println("teams_by_city CITY - list the teams in the specified city");
       System.out.println("load_coaches FILENAME - bulk load of coach info from a file");
       System.out.println("load_teams FILENAME - bulk load of team info from a file");
       System.out
               .println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
       System.out.println(
               "search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
       System.out.println("exit - quit the program");
   }
   /**
   * @param args
   * @throws IOException
   */
   public static void main(String[] args)
   {
       new P1().run();
   }
private class Coach
   {
       private String coach_ID;
       private int year;
       private String first_name;
       private String last_name;
       private int season_win;
       private int season_loss;
       private int playoff_win;
       private int playoff_loss;
       private String team;
      
       public Coach(String coach_ID, int year, String first_name, String last_name, int season_win, int season_loss, int playoff_win, int playoff_loss, String team)
       {
           this.coach_ID = coach_ID;
           this.year = year;
           this.first_name = first_name;
           this.last_name = last_name;
           this.season_win = season_win;
           this.season_loss = season_loss;
           this.playoff_win = playoff_win;
           this.playoff_loss = playoff_loss;
           this.team = team;
       }

public String getCoach_ID(){return coach_ID;}
public int getYear(){return year;}
public String getFirst_name(){return first_name;}
public String getLast_name(){return last_name;}
public int getSeason_win(){return season_win;}
public int getSeason_loss(){return season_loss;}
public int getPlayoff_win(){return playoff_win;}
public int getPlayoff_loss(){return playoff_loss;}
public String getTeam(){return team;}
@Override
public String toString(){return coach_ID + " " + year + " "+ first_name + " " + last_name + " " + season_win + " " + season_loss + " " + playoff_win + " " + playoff_loss + " " + team;}}  
  
   private class Team
   {
       private String team_ID;
       private String location ;
       private String name;
       private String league;
      
       public Team(String team_ID, String location, String name, String league)
       {
           this.team_ID = team_ID;
           this.location = location;
           this.name = name;
           this.league = league;
       }
       public String getLocation()
       {
           return location;
       }
       @Override
       public String toString()
       {
           return team_ID + " " + location + " " + name + " " + league;
       }
   }
}