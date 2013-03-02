onLoadMessage <<= (name,crossVersion,scalaVersion,scalaBinaryVersion,onLoadMessage){ 
  (name,crossV,scalaV,binaryV,currentMessage) =>
  println("\n" + name + "\nscalaVersion = "+ scalaV + ", crossVersion = " + crossV + ", binaryVersion = " + binaryV + "\n")
  currentMessage
}

