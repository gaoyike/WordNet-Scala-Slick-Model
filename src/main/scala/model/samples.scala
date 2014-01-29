package model

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`samples` (
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `sampleid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `sample` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`synsetid`, `sampleid`),
  INDEX `k_samples_synsetid` (`synsetid` ASC),
  CONSTRAINT `fk_samples_synsetid`
    FOREIGN KEY (`synsetid`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
case class samples(synsetid:Int, sampleid:Int, sample:String)
class _samples(tag:Tag) extends Table[samples](tag,"samples"){
  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def sampleid = column[Int]("sampleid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def sample = column[String]("sample", O.DBType("MEDIUMTEXT"), NotNull) // This is the primary key column
  def * = (synsetid,sampleid,sample) <> (samples.tupled, samples.unapply)

  val synset = TableQuery[_synsets]
  def fk_samples_synsetid = foreignKey("fk_samples_synsetid", synsetid, synset)(_.synsetid)

}
