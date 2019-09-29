# find404

Over time static projects tend to accumulate broken links. Given a folder, `find404` will go through every file, ignore any patterns matched by `.gitignore` and aggregate all the urls it finds. It will take each link and make a `get` request to each link. If the status code for any of these requests is a `404` the program will output the problematic link, the name of the file found and the line number. 

# TODO

+ [x] Fork this repo
+ [x] Create a scala file named `Find404.scala`
+ [x] Given a folder via command line args print out all the files in that folder
+ [x] Exclude all the files in `.gitignore`
+ [ ] Read each file that remains and create a `case class UriFound(file: File, line: Int, uri: Uri)`.
+ [ ] Make a `get` request for each `UriFound`. 
+ [ ] If 404 `println` the `UriFound`.
+ [ ] Test this on parthmehrotra.com
+ [ ] Setup CI/CD
+ [ ] Deploy to package managers