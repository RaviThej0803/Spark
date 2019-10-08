


object HousePrice extends App {

  override def main(args: Array[String]): Unit = {
    val spark = org.apache.spark.sql.SparkSession.builder
      .master("local")
      .appName("HousePrice")
      .getOrCreate;

    val data = ModelUtil.loadFile(spark, "kc_house_data.csv")
    val Array(train, test) = ModelUtil.trainTestSplit(data)

    val pipeline = ModelUtil.buildPipeline()
    val predictions = ModelUtil.fitAndTransform(pipeline, train, test)

    EvaluateUtil.evaluateModel(predictions)
  }
}
