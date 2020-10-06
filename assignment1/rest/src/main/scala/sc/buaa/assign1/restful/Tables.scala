package sc.buaa.assign1.restful
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.SQLiteProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = People.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table People
   *  @param id Database column id SqlType(INTEGER), PrimaryKey
   *  @param name Database column name SqlType(TEXT)
   *  @param age Database column age SqlType(INTEGER)
   *  @param gender Database column gender SqlType(NUMERIC) */
  case class PeopleRow(id: Option[Int], name: Option[String], age: Option[Int], gender: Option[Double])
  /** GetResult implicit for fetching PeopleRow objects using plain SQL queries */
  implicit def GetResultPeopleRow(implicit e0: GR[Option[Int]], e1: GR[Option[String]], e2: GR[Option[Double]]): GR[PeopleRow] = GR{
    prs => import prs._
    PeopleRow.tupled((<<?[Int], <<?[String], <<?[Int], <<?[Double]))
  }
  /** Table description of table people. Objects of this class serve as prototypes for rows in queries. */
  class People(_tableTag: Tag) extends profile.api.Table[PeopleRow](_tableTag, "people") {
    def * = (id, name, age, gender) <> (PeopleRow.tupled, PeopleRow.unapply)

    /** Database column id SqlType(INTEGER), PrimaryKey */
    val id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey)
    /** Database column name SqlType(TEXT) */
    val name: Rep[Option[String]] = column[Option[String]]("name")
    /** Database column age SqlType(INTEGER) */
    val age: Rep[Option[Int]] = column[Option[Int]]("age")
    /** Database column gender SqlType(NUMERIC) */
    val gender: Rep[Option[Double]] = column[Option[Double]]("gender")
  }
  /** Collection-like TableQuery object for table People */
  lazy val People = new TableQuery(tag => new People(tag))
}
