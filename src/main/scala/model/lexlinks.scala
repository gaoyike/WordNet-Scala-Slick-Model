package model


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`lexlinks` (
  `synset1id` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `word1id` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `synset2id` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `word2id` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `linkid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`word1id`, `synset1id`, `word2id`, `synset2id`, `linkid`),
  INDEX `k_lexlinks_linkid` (`linkid` ASC),
  INDEX `k_lexlinks_synset1id` (`synset1id` ASC),
  INDEX `k_lexlinks_synset1id_word1id` (`synset1id` ASC, `word1id` ASC),
  INDEX `k_lexlinks_synset2id` (`synset2id` ASC),
  INDEX `k_lexlinks_synset2id_word2id` (`synset2id` ASC, `word2id` ASC),
  INDEX `k_lexlinks_word1id` (`word1id` ASC),
  INDEX `k_lexlinks_word2id` (`word2id` ASC),
  CONSTRAINT `fk_lexlinks_linkid`
    FOREIGN KEY (`linkid`)
    REFERENCES `wordnet31_snapshot`.`linktypes` (`linkid`),
  CONSTRAINT `fk_lexlinks_synset1id`
    FOREIGN KEY (`synset1id`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_lexlinks_synset2id`
    FOREIGN KEY (`synset2id`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_lexlinks_word1id`
    FOREIGN KEY (`word1id`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`),
  CONSTRAINT `fk_lexlinks_word2id`
    FOREIGN KEY (`word2id`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class lexlinks(synset1id:Int, synset2id:Int, word1id:Int, word2id:Int, linkid:Int)
class _lexlinks(tag:Tag) extends Table[lexlinks](tag,"lexlinks"){
  def synset1id = column[Int]("synset1id", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def synset2id = column[Int]("synset2id", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def word1id = column[Int]("word1id", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def word2id = column[Int]("word2id", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def linkid = column[Int]("linkid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def * = (synset1id, synset2id, word1id,word2id,linkid) <> (lexlinks.tupled, lexlinks.unapply)

  val words1 = TableQuery[_words]
  def fk_lexlinks_word1id = foreignKey("fk_lexlinks_word1id", word1id, words1)(_.wordid)

  val words2 = TableQuery[_words]
  def fk_lexlinks_word2id = foreignKey("fk_lexlinks_word2id", word2id, words2)(_.wordid)

  val links = TableQuery[_linktypes]
  def fk_lexlinks_linkid = foreignKey("fk_lexlinks_linkid", linkid, links)(_.linkid)

  val synset1 = TableQuery[_synsets]
  def fk_lexlinks_synset1id = foreignKey("fk_lexlinks_synset1id", synset1id, synset1)(_.synsetid)

  val synset2 = TableQuery[_synsets]
  def fk_lexlinks_synset2id = foreignKey("fk_lexlinks_synset2id", synset2id, synset2)(_.synsetid)


}
