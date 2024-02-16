import java.util.*;

import static java.lang.System.out;

/****************************************************************************************
 * This class implements relational database tables (including attribute names, domains
 * and a list of tuples.  Five basic relational algebra operators are provided: project,
 * select, union, minus and join.  The insert data manipulation operator is also provided.
 * Missing are update and delete data manipulation operators.
 */
public class Table {
    /**
     * Counter for naming temporary tables.
     */
    private static int count = 0;

    /**
     * Table name.
     */
    private final String name;

    /**
     * Array of attribute names.
     */
    private final String[] attribute;

    /**
     * Array of attribute domains: a domain may be
     * integer types: Long, Integer, Short, Byte
     * real types: Double, Float
     * string types: Character, String
     */
    private final Class[] domain;

    /**
     * Collection of tuples (data storage).
     */
    public final List<Comparable[]> tuples;

    /**
     * Primary key.
     */
    private final String[] key;

    /**
     * Index into tuples (maps key to tuple number).
     */
    private final Map<KeyType, Comparable[]> index;

    /**
     * The supported map types.
     */
    private enum MapType {
        TREE_MAP
    }

    /**
     * The map type to be used for indices.  Change as needed.
     */
    private static final MapType mType = MapType.TREE_MAP;

    /************************************************************************************
     * Make a map (index) given the MapType.
     */
    private static Map<KeyType, Comparable[]> makeMap() {
        switch (mType) {
            case TREE_MAP:
                return new TreeMap<>();
            default:
                return null;
        } // switch
    } // makeMap

    //-----------------------------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------------------------

    /************************************************************************************
     * Construct an empty table from the meta-data specifications.
     *
     * @param _name       the name of the relation
     * @param _attribute  the string containing attributes names
     * @param _domain     the string containing attribute domains (data types)
     * @param _key        the primary key
     */
    public Table(String _name, String[] _attribute, Class[] _domain, String[] _key) {
        name = _name;
        attribute = _attribute;
        domain = _domain;
        key = _key;
        tuples = new ArrayList<>();
        index = makeMap();

    } // primary constructor

    /************************************************************************************
     * Construct a table from the meta-data specifications and data in _tuples list.
     *
     * @param _name       the name of the relation
     * @param _attribute  the string containing attributes names
     * @param _domain     the string containing attribute domains (data types)
     * @param _key        the primary key
     * @param _tuples     the list of tuples containing the data
     */
    public Table(String _name, String[] _attribute, Class[] _domain, String[] _key, List<Comparable[]> _tuples) {
        name = _name;
        attribute = _attribute;
        domain = _domain;
        key = _key;
        tuples = _tuples;
        index = makeMap();
    } // constructor

    /************************************************************************************
     * Construct an empty table from the raw string specifications.
     *
     * @param _name       the name of the relation
     * @param attributes  the string containing attributes names
     * @param domains     the string containing attribute domains (data types)
     * @param _key        the primary key
     */
    public Table(String _name, String attributes, String domains, String _key) {
        this(_name, attributes.split(" "), findClass(domains.split(" ")), _key.split(" "));
//        out.println("DDL> create table " + name + " (" + attributes + ")");
    } // constructor

    //----------------------------------------------------------------------------------
    // Public Methods
    //----------------------------------------------------------------------------------

    //++++++++++++++++++++++++++++   SAMPLE IMPLEMENTATION   ++++++++++++++++++++++++++++

    /************************************************************************************
     * Join this table and table2 by performing an "equi-join".  Tuples from both tables
     * are compared requiring attributes1 to equal attributes2.  Disambiguate attribute
     * names by append "2" to the end of any duplicate attribute name.  Implement using
     * a Nested Loop Join algorithm.
     *
     * #usage movie.join ("studioNo", "name", studio)
     *
     * @param attributes1  the attributes of this table to be compared (Foreign Key)
     * @param attributes2  the attributes of table2 to be compared (Primary Key)
     * @param table2      the rhs table in the join operation
     * @return a table with tuples satisfying the equality predicate
     */
    public Table equiJoin(String attributes1, String attributes2, Table table2) {
//        out.println("RA> " + name + ".join (" + attributes1 + ", " + attributes2 + ", " + table2.name + ")");

        String[] t_attrs = attributes1.split(" ");
        String[] u_attrs = attributes2.split(" ");

        List<Comparable[]> rows = new ArrayList<>();

        // Join Operator Starts
        int[] cols1 = match(t_attrs);
        int[] cols2 = table2.match(u_attrs);
        for (int i = 0; i < tuples.size(); i++) {    //for each rows in table1, compare it with
            for (int j = 0; j < table2.tuples.size(); j++) {    //each rows in table2
                boolean attrsValuesEqual = true;
                for (int k = 0; k < cols1.length; k++) {    // compare with each attribute
                    if (!tuples.get(i)[cols1[k]].equals(table2.tuples.get(j)[cols2[k]])) {
                        attrsValuesEqual = false;
                        break;
                    }
                }
                if (attrsValuesEqual) {
                    rows.add(ArrayUtil.concat(tuples.get(i), table2.tuples.get(j)));
                }
            }
        }
        for (int i = 0; i < cols2.length; i++) {
            if (table2.attribute[cols2[i]].equals(attribute[cols1[i]])) {
                table2.attribute[cols2[i]] = table2.attribute[cols2[i]] + "2";
            }
        }
        // Join Operator Ends


        return new Table(name + count++, ArrayUtil.concat(attribute, table2.attribute),
                ArrayUtil.concat(domain, table2.domain), key, rows);
    } // join

    /************************************************************************************
     * Project the tuples onto a lower dimension by keeping only the given attributes.
     * Check whether the original key is included in the projection.
     *
     * #usage movie.project ("title year studioNo")
     *
     * @param attributes  the attributes to project onto
     * @return a table of projected tuples
     */
    public Table project(String attributes) {
        // Split the attributes string into individual attribute names
        String[] attrs = attributes.split(" ");

        // Get the column domain for the projected attributes
        Class[] colDomain = extractDom(match(attrs), domain);

        // Determine the key for the projected table
        String[] newKey = (Arrays.asList(attrs).containsAll(Arrays.asList(key))) ? key : attrs;

        // Create a list to store the projected rows
        List<Comparable[]> rows = new ArrayList<>();

        // Iterate through the rows of the current table
        for (Comparable[] row : this.rows) {
            // Create a new row with only the projected attributes
            Comparable[] projectedRow = new Comparable[attrs.length];
            for (int i = 0; i < attrs.length; i++) {
                int index = match(attrs[i]); // Get the index of the attribute in the current table
                projectedRow[i] = row[index]; // Copy the value at that index to the projected row
            }
            rows.add(projectedRow);  // Add the projected row to the list
        }

        // Create and return the new Table object
        return new Table(name + count++, attrs, colDomain, newKey, rows);
    }

    /************************************************************************************
     * Select the tuples satisfying the given key predicate (key = value).  Use an index
     * (Map) to retrieve the tuple with the given key value.
     *
     * @param keyVal  the given key value
     * @return a table with the tuple satisfying the key predicate
     */
    public Table select(KeyType keyVal) {
        List<Comparable[]> selectedRows = new ArrayList<>();

        // Check if the key is present in the index
        if (index.containsKey(keyVal)) {
            // If found, add the corresponding tuple to the result rows
            selectedRows.add(index.get(keyVal));
        }

        // Return a new table with the selected rows
        return new Table(name + count++, attribute, domain, key, selectedRows);
    } // select


    /************************************************************************************
     * Union this table and table2.  Check that the two tables are compatible.
     *
     * #usage movie.union (show)
     *
     * @param table2  the rhs table in the union operation
     * @return a table representing the union
     */
    public Table union(Table table2) {
        // Check if the tables are compatible
        if (!compatible(table2)) {
            out.println("RA> " + name + ".union (" + table2.name + ") - Incompatible tables");
            return null;
        }

        List<Comparable[]> unionRows = new ArrayList<>();

        // Add tuples from the first table
        unionRows.addAll(tuples);

        // Add tuples from the second table if they are not duplicates
        for (Comparable[] tuple2 : table2.tuples) {
            if (!containsTuple(unionRows, tuple2)) {
                unionRows.add(tuple2);
            }
        }

        // Return a new table with the union result
        return new Table(name + count++, attribute, domain, key, unionRows);
    }

    // Helper method to check if a list of tuples contains a specific tuple
    private boolean containsTuple(List<Comparable[]> tuples, Comparable[] targetTuple) {
        for (Comparable[] tuple : tuples) {
            if (Arrays.equals(tuple, targetTuple)) {
                return true;
            }
        }
        return false;
    }


    /************************************************************************************
     * Take the difference of this table and table2.  Check that the two tables are
     * compatible.
     *
     * #usage movie.minus (show)
     *
     * @param table2  The rhs table in the minus operation
     * @return a table representing the difference
     */
    public Table minus(Table table2) {
        // Check if the tables are compatible
        if (!compatible(table2)) {
            out.println("RA> " + name + ".minus (" + table2.name + ") - Incompatible tables");
            return null;
        }

        List<Comparable[]> minusRows = new ArrayList<>();

        // Iterate through tuples in the first table
        for (Comparable[] tuple1 : tuples) {
            // Check if the tuple is not present in the second table
            if (!containsTuple(table2.tuples, tuple1)) {
                minusRows.add(tuple1);
            }
        }

        // Return a new table with the minus result
        return new Table(name + count++, attribute, domain, key, minusRows);
    }





    /************************************************************************************
     * Join this table and table2 by performing an "natural join".  Tuples from both tables
     * are compared requiring common attributes to be equal.  The duplicate column is also
     * eliminated.
     *
     * #usage movieStar.join (starsIn)
     *
     * @param table2  the rhs table in the join operation
     * @return a table with tuples satisfying the equality predicate
     */
    public Table naturalJoin(Table table2) {
        // Check if the tables are compatible
        if (!compatible(table2)) {
            out.println("RA> " + name + ".naturalJoin (" + table2.name + ") - Incompatible tables");
            return null;
        }

        List<Comparable[]> joinRows = new ArrayList<>();

        // Identify common attributes between the two tables
        List<String> commonAttributes = findCommonAttributes(attribute, table2.attribute);

        // Eliminate duplicate columns
        String[] newAttribute = eliminateDuplicateColumns(attribute, table2.attribute, commonAttributes);
        Class[] newDomain = eliminateDuplicateColumns(domain, table2.domain, commonAttributes);

        // Iterate through tuples in the first table
        for (Comparable[] tuple1 : tuples) {
            // Iterate through tuples in the second table
            for (Comparable[] tuple2 : table2.tuples) {
                // Check if tuples have common attributes
                if (tuplesHaveCommonAttributes(tuple1, tuple2, commonAttributes)) {
                    // Combine tuples and add to result
                    Comparable[] joinedTuple = ArrayUtil.concat(tuple1, tuple2);
                    joinRows.add(joinedTuple);
                }
            }
        }

        // Return a new table with the natural join result
        return new Table(name + count++, newAttribute, newDomain, key, joinRows);
    }

    // Helper method to find common attributes between two attribute arrays
    private List<String> findCommonAttributes(String[] attributes1, String[] attributes2) {
        List<String> commonAttributes = new ArrayList<>();
        for (String attr1 : attributes1) {
            for (String attr2 : attributes2) {
                if (attr1.equals(attr2)) {
                    commonAttributes.add(attr1);
                    break;
                }
            }
        }
        return commonAttributes;
    }

    // Helper method to eliminate duplicate columns
    private <T> T[] eliminateDuplicateColumns(T[] array1, T[] array2, List<String> commonAttributes) {
        List<T> resultList = new ArrayList<>(Arrays.asList(array1));
        for (String commonAttr : commonAttributes) {
            // Rename duplicate columns in the second array
            int index2 = Arrays.asList(array2).indexOf(commonAttr);
            array2[index2] = (T) (commonAttr + "2");
        }
        resultList.addAll(Arrays.asList(array2));
        return resultList.toArray(Arrays.copyOf(array1, 0));
    }

    // Helper method to check if tuples have common attributes
    private boolean tuplesHaveCommonAttributes(Comparable[] tuple1, Comparable[] tuple2, List<String> commonAttributes) {
        for (String commonAttr : commonAttributes) {
            int index1 = Arrays.asList(attribute).indexOf(commonAttr);
            int index2 = Arrays.asList(table2.attribute).indexOf(commonAttr);
            if (!tuple1[index1].equals(tuple2[index2])) {
                return false;
            }
        }
        return true;
    }


    /************************************************************************************
     * Return the column position for the given attribute name.
     *
     * @param attr  the given attribute name
     * @return a column position
     */
    public int col(String attr) {
        for (int i = 0; i < attribute.length; i++) {
            if (attr.equals(attribute[i])) return i;
        } // for

        return -1;  // not found
    } // col

    /************************************************************************************
     * Insert a tuple to the table.
     *
     * #usage movie.insert ("'Star_Wars'", 1977, 124, "T", "Fox", 12345)
     *
     * @param tup  the array of attribute values forming the tuple
     * @return whether insertion was successful
     */
    public boolean insert(Comparable[] tup) {
//        out.println("DML> insert into " + name + " values ( " + Arrays.toString(tup) + " )");

        if (typeCheck(tup)) {
            tuples.add(tup);
            Comparable[] keyVal = new Comparable[key.length];
            int[] cols = match(key);
            for (int j = 0; j < keyVal.length; j++) keyVal[j] = tup[cols[j]];
            {
                index.put(new KeyType(keyVal), tup);
            }
            return true;
        } else {
            return false;
        } // if
    } // insert

    //----------------------------------------------------------------------------------
    // Private Methods
    //----------------------------------------------------------------------------------

    /************************************************************************************
     * Determine whether the two tables (this and table2) are compatible, i.e., have
     * the same number of attributes each with the same corresponding domain.
     *
     * @param table2  the rhs table
     * @return whether the two tables are compatible
     */
    private boolean compatible(Table table2) {
        if (domain.length != table2.domain.length) {
            out.println("compatible ERROR: table have different arity");
            return false;
        } // if
        for (int j = 0; j < domain.length; j++) {
            if (domain[j] != table2.domain[j]) {
                out.println("compatible ERROR: tables disagree on domain " + j);
                return false;
            } // if
        } // for
        return true;
    } // compatible

    /************************************************************************************
     * Match the column and attribute names to determine the domains.
     *
     * @param column  the array of column names
     * @return an array of column index positions
     */
    private int[] match(String[] column) {
        int[] colPos = new int[column.length];

        for (int j = 0; j < column.length; j++) {
            boolean matched = false;
            for (int k = 0; k < attribute.length; k++) {
                if (column[j].equals(attribute[k])) {
                    matched = true;
                    colPos[j] = k;
                } // for
            } // for
            if (!matched) {
                out.println("match: domain not found for " + column[j]);
            } // if
        } // for

        return colPos;
    } // match

    /************************************************************************************
     * Extract the attributes specified by the column array from tuple t.
     *
     * @param t       the tuple to extract from
     * @param column  the array of column names
     * @return a smaller tuple extracted from tuple t
     */
    private Comparable[] extract(Comparable[] t, String[] column) {
        Comparable[] tup = new Comparable[column.length];
        int[] colPos = match(column);
        for (int j = 0; j < column.length; j++) tup[j] = t[colPos[j]];
        return tup;
    } // extract

    /************************************************************************************
     * Check the size of the tuple (number of elements in list) as well as the type of
     * each value to ensure it is from the right domain.
     *
     * @param t  the tuple as a list of attribute values
     * @return whether the tuple has the right size and values that comply
     *          with the given domains
     */
    private boolean typeCheck(Comparable[] t) {
        if (!tuples.isEmpty() && t.length != 0) {
            if (t.length != tuples.get(0).length)
                return false;
            for (int i = 0; i < t.length; i++) {
                if (!t[i].getClass().getSimpleName().equals(tuples.get(0)[i].getClass().getSimpleName()))
                    return false;
            }
        }
        return true;
    } // typeCheck

    /************************************************************************************
     * Find the classes in the "java.lang" package with given names.
     *
     * @param className  the array of class name (e.g., {"Integer", "String"})
     * @return an array of Java classes
     */
    private static Class[] findClass(String[] className) {
        Class[] classArray = new Class[className.length];

        for (int i = 0; i < className.length; i++) {
            try {
                classArray[i] = Class.forName("java.lang." + className[i]);
            } catch (ClassNotFoundException ex) {
                out.println("findClass: " + ex);
            } // try
        } // for

        return classArray;
    } // findClass

    /************************************************************************************
     * Extract the corresponding domains.
     *
     * @param colPos the column positions to extract.
     * @param group  where to extract from
     * @return the extracted domains
     */
    private Class[] extractDom(int[] colPos, Class[] group) {
        Class[] obj = new Class[colPos.length];

        for (int j = 0; j < colPos.length; j++) {
            obj[j] = group[colPos[j]];
        } // for

        return obj;
    } // extractDom

} // Table class
