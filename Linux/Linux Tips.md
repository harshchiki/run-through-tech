# Processes
## Sort processes by memory usage
    `ps aux | sort -nk 4`

## Sort processes by CPU usage
    `ps aux | sort -nk 3`


<br /><br />


# Display output as table
    `<command> | column -t`

    e.g. `mount | column -t`

<br /><br />

# OS information
## Get architecture (e.g. 64, 86)
    `getconf  LONG_BIT`

    output e.g. 64

<br /><br />

# Files/Directories
## Create a file with specific size

    `dd if=/dev/zero of=out.txt bs=1M count=10`

    This will create a file with 10-megabyte size filled with zeroes.

    * `bs` -> block size
    * `count` -> number of blocks (of block size to essentially be created)


<br /><br />

# sed
## 1. Replace space with command in a file (Replace a character with another character in a file)
    `sed -i 's/ /,' <filename>`
    
* Editing happens in place.
* `i` -> "in-place" edit. If not specified, output is generated on console, or as input to the next in "pipe".
* **Note**: This could alternatively be done in `vim` (while is the file is opened in)

    `s/ /,/g` 




<br /><br />