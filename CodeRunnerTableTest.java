public class CodeRunnerTableTest {

    public static void main(String[] args) {
        CodeRunnerTableTest tst = new CodeRunnerTableTest();

        if (tst.testEquiJoin()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Equi Join\" method is incomplete or wrong.");
        }
    }

    /**
     * Generates a movie table for testing
     *
     * @return a sample movie table
     */
    public Table createMovieTable() {
        Table movie = new Table("movie", "title year length genre studioName producerNo",
                "String Integer Integer String String Integer", "title year");
        Comparable[] film1 = {"Star_Wars", 1977, 124, "sciFi", "Fox", 12345};
        Comparable[] film2 = {"Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345};
        Comparable[] film3 = {"Rocky", 1985, 200, "action", "Universal", 12125};
        Comparable[] film4 = {"Rambo", 1978, 100, "action", "Universal", 32355};
        movie.insert(film1);
        movie.insert(film2);
        movie.insert(film3);
        movie.insert(film4);
        return movie;
    }

    /**
     * Generates a studio table for testing
     *
     * @return a sample studio table
     */
    public Table createStudioTable() {
        Table studio = new Table("studio", "name address presNo",
                "String String Integer", "name");
        Comparable[] studio1 = {"Fox", "Los_Angeles", 7777};
        Comparable[] studio2 = {"Universal", "Universal_City", 8888};
        Comparable[] studio3 = {"DreamWorks", "Universal_City", 9999};
        studio.insert(studio1);
        studio.insert(studio2);
        studio.insert(studio3);
        return studio;
    }

    /**
     * Tests the equi join method.
     */
    public boolean testEquiJoin() {
        Table movie = this.createMovieTable();
        Table studio = this.createStudioTable();
        Table eJoin = movie.equiJoin("studioName", "name", studio);

        Comparable studioName = eJoin.tuples.get(0)[eJoin.col("studioName")];
        Comparable name = eJoin.tuples.get(0)[eJoin.col("name")];

        return studioName.equals(name);
    }

    /**
     * Tests the project method.
     */
//    public boolean testProject() {
//    }

    /**
     * Tests the select method.
     */
//    public boolean testSelect() {
//    }

    /**
     * Tests the union method.
     */
//    public boolean testUnion() {
//    }

    /**
     * Tests the minus method.
     */
//    public boolean testMinus() {
//    }

    /**
     * Tests the natural join method.
     */
//    public boolean testNaturalJoin() {
//    }
}
