package DAL.Repo;

public class RepoFactory {
    public static IRepo getSqlRepo() {
        return new SqlRepo();
    }
}
