# git rebase work flow

1. checkout public branch **ParentBranch**: 

	`git checkout ParentBranch`

	update ParentBranch, or add, modified on ParentBranch. 

	commit your changes.

2. checkout personal private working branch
	
	PersonalBranch which's parent branch is ParentBranch: 

	`git checkout PersonalBranch`

3. add, modified files on working branch PersonalBranch.
4. commit changes: git commit -m 'do something cool'
5. rebase working branch: git rebase -i ParentBranch PersonalBranch
6. checkout ParentBranch: git checkout ParentBranch
7. merge working branch `PersonalBranch` into public branch `ParentBranch`: git merge -v PersonalBranch

------

# code example

1. step 1: pull remote public branch

```bash
git clone https://github.com/xechoz/demo.git 

git checkout -b feature-hello # create working branch
git checkout master # back to public branch

# add some files on public branch
echo "public branch file" >> public.md

git add public.md

# Note: this commit is on public branch, but not private working branch feature-hello
git commit -m 'add public file' 
```

2. step 2: checkout working branch

```bash
git checkout -b feature-hello

# or if feature-hello is exist
git checkout feature-hello
```

3. step 3 : add, modified files

```
echo "hello" >> public.md

# add more files
echo "hello file" >> hello.md
```
4. step 4: commit changes

```
git add public.md
git commit -m 'update public file'


git add hello.md
git commit -m 'hello world'
```

5. step 5: rebase working branch

```bash
# Note: this make feature-hello update-to-date with master
git rebase -i master feature-hello 
```

6. step 6: checkout back to public branch

```bash
git checkout master
```

7. step 7: merge commits on private working branch

```
# because feature-hello is update-to-date with master, so there is no redundant 'merge-commit' here
git merge -v feature-hello
```