Stream.of(
Block.box(5, 0, 5, 11, 2, 11),
Block.box(6.5, 2, 6.5, 9.5, 4, 9.5),
Block.box(6.5, 10, 6.5, 9.5, 12, 9.5),
Block.box(7, 4, 7, 9, 18, 9),
Block.box(1.25, 18, 6.5, 14.75, 25, 9.5),
Block.box(12.25, 25, 7, 14.25, 28, 9),
Block.box(1.75, 25, 7, 3.75, 28, 9),
Block.box(7, 25, 7, 9, 29, 9)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();