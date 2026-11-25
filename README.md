# WordTrees

A plain Java word-frequency tree implementation. It reads words from a text file, stores them in a binary search tree, and writes frequency statistics to an output file. There are no external dependencies.

## Requirements

- Java 25 runtime (`java` on `PATH`).

## Run the main CLI

From the repository root:

```bash
printf 'DEFARGE\nMAN\nDE\n' | java src/RunArvorePalavras.java texto resultado src/ArvorePalavras2.java src/ConjuntoPalavraFrequencia.java
```

The first argument is the input file and the second is the output file. Defaults are `texto` and `resultado`.

The program asks for:

- one word to look up,
- another word to look up,
- a prefix to search.

It then writes the full tree, the two frequencies, the number of distinct words between the two lookup words, the prefix matches, the tree after removing the first word, and the most frequent word(s) to the output file.

## Run the smoke test

```bash
java src/TryRunArvore.java src/ArvorePalavras2.java src/ConjuntoPalavraFrequencia.java
```

## Fixes included

- Removed shadowing generic parameters from `ArvorePalavras2.Node`.
- Made insertion, lookup, prefix search, and range counts case-insensitive.
- Implemented `entrePalavras` as an exclusive lexicographic range count.
- `maxFrequencia` now returns every word tied for the highest frequency.
- Replaced the custom iterator with a standard `Iterator<String>` implementation.
- Removed debug output from `ArvorePalavras` and the smoke test.
- Translated CLI prompts to English.

## Notes

- `src/ArvorePalavras.java` is the original experimental version and is kept for reference.
- Sample data files `texto` and `resultado` are preserved.

## License

MIT — see `LICENSE`.
