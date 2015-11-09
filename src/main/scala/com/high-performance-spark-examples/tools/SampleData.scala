import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.random.RandomRDDs

/**
 * Sample our production data to be able to use it for tests
 */
object SampleData {
  /**
   * Sample the input down to k % for usage in tests
   */
  def sampleInput[T](rdd: RDD[T]): RDD[T] = {
  // tag::randomSampleInput[]
    rdd.sample(withReplacement=false, fraction=0.1)
  // end::randomSampleInput[]
  }

  /**
   * Construct a stratified sample
   */
  def stratifiedSample(rdd: RDD[(String, Array[Double])]): RDD[(String, Array[Double])] = {
    // tag::stratifiedSample[]
    // 5% of the red pandas, and 50% of the giant pandas
    val stratas = Map("red" -> 0.05, "giant" -> 0.50)
    rdd.sampleByKey(withReplacement=false, fractions = stratas)
    // end::stratifiedSample[]
  }
}