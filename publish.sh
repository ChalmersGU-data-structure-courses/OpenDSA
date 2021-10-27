#!/bin/bash

# Publish compiled book(s) and source code to the directory Published/

rm -rf Published
mkdir -p Published

echo "Copying books:" Books/* "--> Published/"
cp -r Books/* Published/

shopt -s nullglob

for lang in Pseudo Java Python ; do
    fromdir=SourceCode/$lang*/ChalmersGU
    todir=Published/SourceCode/$lang
    echo "Copying code: $fromdir --> $todir"
    mkdir -p $todir
    for fromfile in $fromdir/*.{java,py,txt,md} ; do
        tofile=$todir/`basename $fromfile`
	grep -v  '/\* \*\*\* ODSA' $fromfile > $tofile
    done
done

