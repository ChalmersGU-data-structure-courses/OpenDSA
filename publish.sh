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
    for ext in .java .py .txt .md Makefile ; do
        for fromfile in `find $fromdir -name '*'$ext` ; do
            if [[ ! $fromfile =~ "build" ]]; then
                tofile=$todir${fromfile/*ChalmersGU/}
                mkdir -p `dirname $tofile`
	        grep -v  '/\* \*\*\* ODSA' $fromfile > $tofile
            fi
        done
    done
done

