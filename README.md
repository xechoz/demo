# demo

# branches

All branches prefix with 'feature-', etc. feature-git, feature-animate,

are use to distince the demo code functions. For example, feature-git is use for

testing git command, etc. rebase, merge usages.

# 笔记

## merge vs rebase

1. 什么时候使用 rebase？

2. 正确的使用分支习惯

    1. remote origin branch: foo
    2. user A branch: foo_a -> origin/foo
    3. user B branch: foo_b -> origin/foo

假如 A, B 的最近共同 commit 是 commit-1;

A local commits `commit-a1, commit-a2`, and pushed to remote. Now, remote commits become `commit-1 -> commit-a1 -> commit-a2`;


B local commit : `commit-b1`. B's commit flow: `commit-1 -> commit-b1`

now comes the key point, should B run  `git merge origin/foo fooB` or `git rebase origin/foo fooB ` ?

both of the commands can **merge** A's work into B's, but with `git merge origin/foo fooB` a redundant **merge commit** wit
# more
 
